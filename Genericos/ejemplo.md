```java
// Repositorio.java
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    T create(T entity);           // Create
    Optional<T> read(ID id);      // Read
    List<T> readAll();            // Read All
    T update(T entity);           // Update
    boolean delete(ID id);        // Delete
}
```

```java
// InMemoryRepository.java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class InMemoryRepository<T, ID> implements Repository<T, ID> {
    protected List<T> database = new ArrayList<>();

    @Override
    public T create(T entity) {
        database.add(entity);
        return entity;
    }

    @Override
    public Optional<T> read(ID id) {
        return database.stream()
                .filter(entity -> getId(entity).equals(id))
                .findFirst();
    }

    @Override
    public List<T> readAll() {
        return new ArrayList<>(database);
    }

    @Override
    public T update(T entity) {
        read(getId(entity)).ifPresent(existingEntity -> {
            int index = database.indexOf(existingEntity);
            database.set(index, entity);
        });
        return entity;
    }

    @Override
    public boolean delete(ID id) {
        return read(id).map(entity -> database.remove(entity)).orElse(false);
    }

    protected abstract ID getId(T entity);
}
```

```java
// Customer.java
public class Customer {
    private Long id;
    private String name;
    private String email;

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
```

```java
// CustomerRepository.java
public class CustomerRepository extends InMemoryRepository<Customer, Long> {

    @Override
    protected Long getId(Customer customer) {
        return customer.getId();
    }
}
```

```java
// AppBankingCrud.java
public class AppBankingCrud {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepository();

        // Create customers
        Customer customer1 = new Customer(1L, "John Doe", "john.doe@example.com");
        Customer customer2 = new Customer(2L, "Mary Smith", "mary.smith@example.com");

        customerRepository.create(customer1);
        customerRepository.create(customer2);

        // Read all customers
        System.out.println("List of customers:");
        customerRepository.readAll().forEach(System.out::println);

        // Read a customer by ID
        System.out.println("\nCustomer with ID 1:");
        customerRepository.read(1L).ifPresent(System.out::println);

        // Update a customer
        customer1.setName("John Doe Updated");
        customerRepository.update(customer1);

        System.out.println("\nUpdated customer:");
        customerRepository.read(1L).ifPresent(System.out::println);

        // Delete a customer
        customerRepository.delete(2L);

        System.out.println("\nList of customers after deletion:");
        customerRepository.readAll().forEach(System.out::println);
    }
}
```