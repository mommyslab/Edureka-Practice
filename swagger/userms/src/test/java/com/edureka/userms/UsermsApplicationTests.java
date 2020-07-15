package com.edureka.userms;

/*import org.junit.Test;
import org.junit.runner.RunWith;*/
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UsermsApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("Hello World");
	}

}
    // an example of writing test
	/*@Test
	public void should_create_valid_book_and_return_created_status() throws Exception {
		Book book = new Book("123-1234567890","My new book","Publisher");
		book.addAuthor(new Author("John","Doe"));
		mockMvc.perform(post("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(book)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", is("http://localhost/api/books/123-1234567890")))
				.andExpect(content().string(""))
				.andDo(MockMvcResultHandlers.print());
	}*/
