package web.dto;

import fr.iut.montreuil.lpcsid.config.DozerConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mélina on 13/04/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DozerConfig.class})
public class AccountDtoTest {

    /*    @Autowired
        private Mapper mapper;

        @Test
        public void should_SerializeDTOToPojo_OnAccount() {
            long id = 1L;
            boolean checked = true;
            String description = "descr";
           AccountEntity account = newAccountEntity().withId(id).withChecked(checked).withDescription(description).build();

            TodoDTO todoDTO = mapper.map(todo, TodoDTO.class);

            assertThat(todoDTO, notNullValue());
            assertThat(todoDTO.getId(), is(id));
            assertThat(todoDTO.isChecked(), is(checked));
            assertThat(todoDTO.getDescription(), is(description));
        }

        @Test
        public void should_SerializePojoToDTO_OnTodo() {
            long id = 1L;
            boolean checked = true;
            String description = "descr";
            TodoDTO todoDTO = newTodoDTO().withId(id).withChecked(checked).withDescription(description).build();

            Todo todo = mapper.map(todoDTO, Todo.class);

            assertThat(todo, notNullValue());
            assertThat(todo.getId(), is(id));
            assertThat(todo.isChecked(), is(checked));
            assertThat(todo.getDescription(), is(description));
        }
    }*/
}
