package ru.kastricyn.alfatest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kastricyn.alfatest.model.ExchangeRates;
import ru.kastricyn.alfatest.model.PairForCompare;
import ru.kastricyn.alfatest.model.TagsForGIF;
import ru.kastricyn.alfatest.service.CompareCurrencyService;
import ru.kastricyn.alfatest.service.GIFService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class CompareCurrencyControllerTest {

  final Map<String, Double> exMap = new HashMap<>();
  final ExchangeRates exchange = new ExchangeRates(10524, "USD", exMap);
  final PairForCompare pairForCompare = new PairForCompare(56.765, 53.456);
  final String uriForGIF = "justSomeURLforGIF";

  @Autowired private MockMvc mockMvc;
  @MockBean private CompareCurrencyService compareCurrencyService;
  @MockBean private GIFService gifService;

  {
    exMap.put("GBP", 1.77);
    exMap.put("RUB", 77.6);
  }

  protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
    return mockMvc.perform(builder);
  }

  @BeforeEach
  void beforeEach() {
    when(compareCurrencyService.getPairValuesForCompare("RUB")).thenReturn(pairForCompare);
    when(compareCurrencyService.getTagForGIF(pairForCompare)).thenReturn(TagsForGIF.RICH);
    when(gifService.getURLForGIF(TagsForGIF.RICH.name())).thenReturn(uriForGIF);
  }

  @Test
  void getGif() throws Exception {
    var ans = "<img src = '" + uriForGIF + "'></img><br>" + TagsForGIF.RICH.name();
    mockMvc
        .perform(MockMvcRequestBuilders.get("/"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().string(ans));
  }
}
