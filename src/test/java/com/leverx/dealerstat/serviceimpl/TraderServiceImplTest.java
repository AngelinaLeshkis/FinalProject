package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.entity.Trader;
import com.leverx.dealerstat.persistence.TraderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraderServiceImplTest {

    @Autowired
    private TraderServiceImpl traderService;

    @MockBean
    private TraderRepository traderRepo;

    @Test
    void saveTrader() {

        Trader trader = new Trader();

        Trader savedTrader = traderService.saveTrader(trader);

        Assert.assertNotNull(savedTrader);

        Mockito.verify(traderRepo, Mockito.times(1)).save(trader);
    }
}
