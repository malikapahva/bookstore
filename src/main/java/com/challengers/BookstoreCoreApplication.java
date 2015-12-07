package com.challengers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookstoreCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BookstoreCoreApplication.class, args);
        /*TransactionRepository transactionRepository = applicationContext.getBean(TransactionRepository.class);
        Set<BookTransactionInfo> bookTransactionInfos = new HashSet<>();
        bookTransactionInfos.add(new BookTransactionInfo(1978185706830476223L, 1));
        Transaction transaction = new Transaction(8670074797002309331L, bookTransactionInfos);
        transactionRepository.save(transaction);*/

       /* BookRepository bookRepository = applicationContext.getBean(BookRepository.class);
        bookRepository.deleteAll();
        System.out.println("books deleted#######################");
        List<Book> books = new ArrayList<>();
        for(int i =0; i < 10000; i++) {
            Set<String> authors = new HashSet<>();
            authors.add("John Benoit");
            authors.add("Tony Cole");
            Set<String> publishers = new HashSet<>();
            publishers.add("abc publications");
            Book book1 = new Book("NoSQL", authors, publishers, 2004, "abc123456789", "English", 30.50, 5, 2);
            books.add(book1);
        }
        bookRepository.save(books);

        System.out.println("Books Created " + bookRepository.findAll().size());*/

      /*  UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        User user1 = new User("malika", "malika", "Malika Pahva");
        User user2 = new User("sandesh", "sandesh", "Sandesh");
        User user3 = new User("sufian", "sufian", "Sufian");*/

        // Add Users to database
        /*List<User> users = Arrays.asList(user1, user2, user3);
        userRepository.save(users);*/

        //Delete a user
        //userRepository.delete(2725442848095386635L);

        //Find all users
       /* List<User> all = userRepository.findAll();
        for (User user : all) {
            System.out.println(user.getFirstName());
        }
*/
        //update name
       /* User userToUpdate = userRepository.findOne(4696283733126237149L);
        userToUpdate.setFirstName("Malika");
        userRepository.save(userToUpdate);*/
       /* User userByFirstName = userRepository.findByFirstName("Malika");
        System.out.println(userByFirstName);*/
    }
}
