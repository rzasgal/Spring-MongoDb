import com.usb.springmongodb.dao.PersonDao;
import com.usb.springmongodb.models.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-servlet.xml"})
@WebAppConfiguration
public class PersonDaoTest {


    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    PersonDao personDao;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void insertTest(){
        Person person = new Person();
        person.setName("Test");
        person.setSurname("Test");
        person.setId("999");
        person.setPhoneNumber("5555555555");
        personDao.insert(person);
        Person foundPerson = personDao.findById("999");
        assert foundPerson!= null && foundPerson.getName().equals("Test");
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        person.setName("Test");
        person.setSurname("Test");
        person.setId("999");
        person.setPhoneNumber("5555555555");
        personDao.insert(person);
        personDao.delete(person);
        Person foundPerson = personDao.findById("999");
        assert foundPerson == null;
    }
}
