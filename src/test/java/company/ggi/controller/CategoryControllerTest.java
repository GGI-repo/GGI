package company.ggi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.CategoryDao;
import company.ggi.exception.CategoryException;
import company.ggi.model.Category;
import company.ggi.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Ismail ELFAQIR on 11/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryDao categoryDao;

    private MockMvc mockMvc;

    private final String categoryUrl = "/admin/category/";
    private final String findUrl = categoryUrl + "find";

    private ObjectMapper mapper;

    private List<Category> categoriesFactory;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws Exception {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.mapper = new ObjectMapper();
        this.getData();
    }

    @Test
    public void testGetAllCategories() throws Exception {

        when(categoryService.findAll()).thenReturn(categoriesFactory);
        mockMvc.perform(get(categoryUrl))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateCategory() throws Exception {

        Category test = new Category("example");
        MvcResult result = mockMvc.perform(post(categoryUrl)
                .param("name", "" + test.getName()))
                .andExpect(status().isOk())
                .andReturn();
        Category res = (Category) mapper.readValue(result.getResponse().getContentAsString(), Category.class);
        Assert.assertTrue(test.getName().equals(res.getName()));
    }

    @Test
    public void testCreateCategoryWithException() throws Exception {

        when(categoryService.create(any(Category.class))).thenThrow(new CategoryException(CategoryException.CATEGORY_NOT_FOUND));
        mockMvc.perform(post(categoryUrl)
                .param("id", "" + categoriesFactory.get(0).getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateCategory() throws Exception {
/*
        Category test = categoriesFactory.get(0);
        test.setName("testUpdate");

        when(categoryService.update(any(Category.class))).thenReturn(test);
        MvcResult result = mockMvc.perform(put(updateUrl)
                .param("id", "" + test.getId())
                .param("name", test.getName()))
                .andExpect(status().isBadRequest())
                .andReturn();
        Assert.assertTrue(result.getResponse().getContentAsString().equals(mapper.writeValueAsString(test)));*/
    }

    @Test
    public void testUpdateCategoryWithException() throws Exception {

        when(categoryService.update(any(Category.class))).thenThrow(new CategoryException(CategoryException.CATEGORY_NOT_FOUND));
        mockMvc.perform(put(categoryUrl)
                .param("id", "" + categoriesFactory.get(0).getId()))
                .andExpect(status().isBadRequest());
    }

    private void getData() throws Exception {

        categoriesFactory = new ArrayList<Category>();

        Category category = new Category("test");
        category.setId(1);

        Category category2 = new Category("test2");
        category.setId(2);

        categoriesFactory.add(category);
        categoriesFactory.add(category2);
    }
}
