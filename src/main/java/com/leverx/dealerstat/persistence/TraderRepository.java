package com.leverx.dealerstat.persistence;

import com.leverx.dealerstat.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<Trader, Long> {

    Trader findTraderByNameOfTrader(String nameOfTrader);
}
