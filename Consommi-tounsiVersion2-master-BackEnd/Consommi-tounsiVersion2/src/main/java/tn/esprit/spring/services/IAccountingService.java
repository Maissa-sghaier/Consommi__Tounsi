package tn.esprit.spring.services;

import tn.esprit.spring.entity.Accounting;

public interface IAccountingService {

	double getOutcomeAd();

	double getOutcomeEvent();

	double getOutcomeDelivaryMan();

	//double getIncomeOrder();

	double getIncomeFund();

	//double getTotalIncome();

	double getTotalOutcome();

	Accounting addAccounting();

	Accounting updateAccounting();

	double gain_permonth();

	double totalInc();

	double totalOut();

	double getIncomeOrder();

	double getTotalIncome();

}
