package pl.brzozowski.maciej.recrutationpointofsale.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.brzozowski.maciej.recrutationpointofsale.configuration.UrlTemplate.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String body, badBody;
    private String contentType;

    @Before
    public void setUp() throws Exception {
        body = "{\"barcode\":\"1234\"}";
        badBody = "{\"barcode\":\"1234abc\"}";
        contentType = "application/json";
    }

    @Test
    public void testShouldCheckBarcodeandReturnSuccesWhenBarcodeExistsInDB() throws Exception {

        mockMvc.perform(post(POS + CHECK_BARCODE).content(body).contentType(contentType))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testShouldCheckBarcodeandReturnErrorWhenBarcodeNotExistsInDB() throws Exception {
        mockMvc.perform(post(POS + CHECK_BARCODE).content(badBody).contentType(contentType))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldAddProductAndReturnSuccessWhenProductExistsInDB() throws Exception {
        mockMvc.perform(post(POS + ADD_PRODUCT).content(body).contentType(contentType))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldNotProductAndReturnSuccessWhenProductNotExistsInDB() throws Exception {
        mockMvc.perform(post(POS + ADD_PRODUCT).content(badBody).contentType(contentType))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void testShouldGetListOfAllAddedProducts() throws Exception {
        mockMvc.perform(get(POS + GET_PRODUCTS)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void shouldCloseReceiptAndReturnAllAddedProduct() throws Exception {
        mockMvc.perform(get(POS + PRINT)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}