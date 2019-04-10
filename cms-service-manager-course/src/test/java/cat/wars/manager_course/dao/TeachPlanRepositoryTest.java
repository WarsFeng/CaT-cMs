package cat.wars.manager_course.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeachPlanRepositoryTest {

    @Autowired
    private TeachPlanRepository repository;

    @Test
    public void testExists() {
        System.out.println(repository.existsByCourseid("297e7c7c62b888f00162b8a965510001a"));
    }


    @Test
    public void testQuery() {
        Optional<String> stringOptional = repository.findCourseidById("asd1");
        stringOptional.ifPresent(System.out::println);
    }

    @Test
    public void testFindOrderBy() {
        System.out.println(repository.findOrderbyByParentId("4028e58161bd3b380161bd3e47da0003aa"));
    }
}