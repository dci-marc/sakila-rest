#!/bin/bash

SWD="$(dirname "$(readlink -f "${BASH_SOURCE[0]}")")"

(cd "${SWD}/.." && ./mvnw springdoc-openapi:generate || exit 1)