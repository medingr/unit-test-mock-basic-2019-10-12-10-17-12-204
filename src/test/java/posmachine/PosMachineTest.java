package posmachine;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);

        //when

        //then
        assertThrows(UnsupportedOperationException.class, () -> {
            posMachine.getReceipt("Coke");
        });
    }

    @Test
    public void should_get_receipt_using_stub_price_calculator() {
        //given
        PriceCalculator priceCalculator = new StubPriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);

        //when
        String receipt = posMachine.getReceipt("Coke");
        //then
        assertEquals("Name: Coke, Price: 20.0", receipt);
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        PriceCalculator priceCalculator = Mockito.mock(PriceCalculator.class);
        PosMachine posMachine = new PosMachine(priceCalculator);
        String productName = "Coke";

        //when
        when(priceCalculator.calculate(productName)).thenReturn(20.0);
        String receipt = posMachine.getReceipt(productName);

        //then
        assertEquals("Name: Coke, Price: 20.0", receipt);
    }

    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 20.0;
        }
    }
}
