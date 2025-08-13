#!/usr/bin/env bash
set -euo pipefail

SRC_DIR="src/main/resources/db/migration/mariadb"
DST_DIR="src/main/resources/db/migration/postgresql"

mkdir -p "$DST_DIR"

mkdir -p "$DST_DIR"

for file in "$SRC_DIR"/*.sql; do
    filename=$(basename "$file")
    newfile="$DST_DIR/${filename%.sql}.sql"
    echo "Convert: $file → $newfile"

    tmpfile=$(mktemp)

    sed -E \
        -e 's/`//g' \
        -e 's/\r//g' \
        -e 's/\bBIGINT\s+AUTO_INCREMENT/BIGSERIAL/gI' \
        -e 's/\bINT\s+AUTO_INCREMENT/SERIAL/gI' \
        -e 's/\bINTEGER\s+AUTO_INCREMENT/SERIAL/gI' \
        -e 's/\bSMALLINT\s+AUTO_INCREMENT/SMALLSERIAL/gI' \
        -e 's/\s+AUTO_INCREMENT//gI' \
        -e 's/ENGINE=[^ ]*//gI' \
        -e 's/DEFAULT CHARSET=[^ ]*//gI' \
        -e 's/COLLATE\s+[^ ]*//gI' \
        -e 's/TINYINT\(1\)/BOOLEAN/gI' \
        -e 's/UNSIGNED//gI' \
        -e 's/\bDATETIME\b/TIMESTAMP/gI' \
        -e 's/\bNOW\s*\(\s*\)/CURRENT_TIMESTAMP/gI' \
        -e 's/\bDOUBLE\b/DOUBLE PRECISION/gI' \
        -e 's/\bFLOAT\b/REAL/gI' \
        -e 's/ON UPDATE CURRENT_TIMESTAMP//gI' \
        "$file" > "$tmpfile"

    awk '
    BEGIN { in_table=0; table_name="" }
    {
        line=$0
        if (match(line, /CREATE TABLE ([^ ]+)\s*\(/, m)) {
            in_table=1
            table_name=m[1]
        }
        if (in_table && match(line, /KEY ([^ ]+) \(([^)]+)\)/, k)) {
            idx_name=k[1]
            col=k[2]
            print "CREATE INDEX " idx_name " ON " table_name "(" col ");"
            next
        }
        if (in_table && line ~ /\);/) { in_table=0 }
        print line
    }' "$tmpfile" > "$newfile"

    rm "$tmpfile"
done

echo "✅ Done: $DST_DIR"
