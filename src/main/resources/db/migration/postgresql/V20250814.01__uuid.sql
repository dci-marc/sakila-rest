CREATE EXTENSION IF NOT EXISTS pgcrypto;

ALTER TABLE "film" ADD COLUMN "uuid" UUID;
UPDATE "film"
SET "uuid" = gen_random_uuid()
WHERE "uuid" IS NULL;
ALTER TABLE "film"
ADD CONSTRAINT "film_uuid_unique" UNIQUE ("uuid");
ALTER TABLE "film"
ALTER COLUMN "uuid" SET NOT NULL;

ALTER TABLE "store" ADD COLUMN "uuid" UUID;
UPDATE "store"
SET "uuid" = gen_random_uuid()
WHERE "uuid" IS NULL;
ALTER TABLE "store"
ADD CONSTRAINT "store_uuid_unique" UNIQUE ("uuid");
ALTER TABLE "store"
ALTER COLUMN "uuid" SET NOT NULL;

ALTER TABLE "customer" ADD COLUMN "uuid" UUID;
UPDATE "customer"
SET "uuid" = gen_random_uuid()
WHERE "uuid" IS NULL;
ALTER TABLE "customer"
ADD CONSTRAINT "customer_uuid_unique" UNIQUE ("uuid");
ALTER TABLE "customer"
ALTER COLUMN "uuid" SET NOT NULL;
