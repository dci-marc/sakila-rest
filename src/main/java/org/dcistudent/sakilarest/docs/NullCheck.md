## ✅ `assert` vs `Optional` vs `Objects.requireNonNull` in Java

### 1. **Purpose Comparison**

| Use Case                        | Best Approach                    |
|---------------------------------|----------------------------------|
| Precondition checks             | `Objects.requireNonNull(...)`    |
| Optional return values          | `Optional<T>`                    |
| Internal developer assumptions  | `assert`                         |
| Runtime parameter validation    | `Objects.requireNonNull(...)`    |
| Avoiding `null` in return types | `Optional<T>`                    |
| Avoiding `null` in fields       | Use default value or encapsulate |

---

### 2. **Feature Comparison**

| Feature                   | `assert`                   | `Optional<T>`                  | `Objects.requireNonNull(...)`       |
|---------------------------|----------------------------|--------------------------------|-------------------------------------|
| Checked at runtime        | Only when `-ea` is enabled | Always                         | Always                              |
| Indicates optionality     | ❌                          | ✅                              | ❌                                   |
| Used for input validation | 🚫 Not recommended         | 🚫 Not recommended             | ✅                                   |
| Used for return values    | 🚫 No                      | ✅ Recommended                  | 🚫 No                               |
| Produces exception        | `AssertionError`           | No (unless `get()` is misused) | `NullPointerException` with message |
| Suitable for production   | ❌ Usually not enabled      | ✅                              | ✅                                   |
| Overhead                  | Low                        | Moderate                       | Low                                 |

---

### 3. **Typical Usage Examples**

#### `assert`

```java
assert data !=null:"Data must not be null";
```

> ⚠️ Use for development/debugging only. Not reliable in production unless `-ea` is used.

---

#### `Optional`

```java
Optional<String> findUserById(String id) {
  // user might not be found
  return Optional.ofNullable(database.find(id));
}
```

> ✅ Use for **optional return values**, never for method parameters.

---

#### `Objects.requireNonNull`

```java
public void process(Data data) {
  this.data = Objects.requireNonNull(data, "Data must not be null");
}
```

> ✅ Use for validating that inputs or internal fields are never `null`.

---

### 4. **Best Practice Summary**

* ✅ Use `Optional<T>` **only** for return values that may be absent.
* ✅ Use `Objects.requireNonNull(...)` for **mandatory input checks**.
* ⚠️ Use `assert` only for **internal assumptions**, not critical logic.
* 🚫 **Never use `Optional` as a method parameter.**

---
