package com.rogrand.job;

import com.rogrand.core.dao.SqlDao;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.enums.IncomeType;
import com.rogrand.tuozhanbao.domain.IncomeRecords;
import com.rogrand.tuozhanbao.domain.TradeRecords;
import com.rogrand.tuozhanbao.domain.UserInfo;
import com.rogrand.tuozhanbao.enums.UserGrade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class QuartzJob
{
  @Autowired
  @Qualifier("sqlDao")
  protected SqlDao sqlDao;

  public void computeIncome() {
    System.out.println("开始计算");


    List<TradeRecords> tradeRecordsList = this.sqlDao.list("ape_trade_records.selectTradeRecords");
    for (int j = 0; j < tradeRecordsList.size(); j++) {
      TradeRecords tradeRecords = tradeRecordsList.get(j);
      Long user_id = tradeRecords.getUser_id();
      if (user_id != null) {

        PageParam param1 = new PageParam();
        param1.put("id", user_id);
        UserInfo userInfo = (UserInfo)this.sqlDao.query("ape_user_info.pageList", param1);
        if (userInfo != null) {

          BigDecimal rate = getRate(userInfo.getNumber());
          if (rate != null && rate.compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal surplus_rate = rate.subtract(new BigDecimal("2"));
            if (surplus_rate.compareTo(BigDecimal.ZERO) > 0) {
              updateUserIncome(userInfo, tradeRecords, surplus_rate);
            }


            Long recommon_id = userInfo.getRecommon_id();
            if (recommon_id != null && recommon_id.longValue() != 0L)

            {
              PageParam param = new PageParam();
              param.put("id", recommon_id);
              UserInfo userInfo1 = (UserInfo)this.sqlDao.query("ape_user_info.pageList", param);
              if (userInfo1 != null)
                computeIncome2(userInfo1, tradeRecords, rate);  }
          }
        }
      }
    }  System.out.println("计算结束");
  }






  private void computeIncome2(UserInfo userInfo, TradeRecords tradeRecords, BigDecimal rate) {
    BigDecimal rate2 = getRate(userInfo.getNumber());
    if (rate2 == null || rate2.compareTo(BigDecimal.ZERO) <= 0)
      return;
    BigDecimal surplus_rate = rate2.subtract(rate);

    if (surplus_rate.compareTo(BigDecimal.ZERO) > 0) {

      updateUserIncome(userInfo, tradeRecords, surplus_rate);
    } else {
      rate2 = rate;
    }


    Long recommon_id = userInfo.getRecommon_id();
    if (recommon_id != null && recommon_id.longValue() != 0L) {
      PageParam param = new PageParam();
      param.put("id", recommon_id);
      UserInfo userInfo1 = (UserInfo)this.sqlDao.query("ape_user_info.pageList", param);
      if (userInfo1 != null) {
        computeIncome2(userInfo1, tradeRecords, rate2);
      }
    }
  }






  private BigDecimal getRate(String user_number) {
    String grade = (String)this.sqlDao.query("ape_user_info.selectGradeByNumber", user_number);
    if (StringUtils.isEmpty(grade)) {
      BigDecimal trade_amount = (BigDecimal)this.sqlDao.query("ape_trade_records.selectTradeAmount", user_number);
      if (trade_amount == null) trade_amount = BigDecimal.ZERO;

      if (trade_amount.compareTo(BigDecimal.ZERO) >= 0 && trade_amount.compareTo(new BigDecimal(1000000)) < 0) grade = UserGrade.V1.getValue();
      if (trade_amount.compareTo(new BigDecimal(1000000)) >= 0 && trade_amount.compareTo(new BigDecimal(4000000)) < 0) grade = UserGrade.V2.getValue();
      if (trade_amount.compareTo(new BigDecimal(4000000)) >= 0 && trade_amount.compareTo(new BigDecimal(8000000)) < 0) grade = UserGrade.V3.getValue();
      if (trade_amount.compareTo(new BigDecimal(8000000)) >= 0 && trade_amount.compareTo(new BigDecimal(15000000)) < 0) grade = UserGrade.V4.getValue();
      if (trade_amount.compareTo(new BigDecimal(15000000)) >= 0 && trade_amount.compareTo(new BigDecimal(30000000)) < 0) grade = UserGrade.V5.getValue();
      if (trade_amount.compareTo(new BigDecimal(30000000)) >= 0 && trade_amount.compareTo(new BigDecimal(100000000)) < 0) grade = UserGrade.V6.getValue();
      if (trade_amount.compareTo(new BigDecimal(100000000)) >= 0 && trade_amount.compareTo(new BigDecimal(300000000)) < 0) grade = UserGrade.V7.getValue();
      if (trade_amount.compareTo(new BigDecimal(300000000)) >= 0 && trade_amount.compareTo(new BigDecimal(800000000)) < 0) grade = UserGrade.V8.getValue();
      if (trade_amount.compareTo(new BigDecimal(800000000)) >= 0) grade = UserGrade.V9.getValue();

    }
    if (UserGrade.V1.getValue().equals(grade)) return new BigDecimal("2");
    if (UserGrade.V2.getValue().equals(grade)) return new BigDecimal("5");
    if (UserGrade.V3.getValue().equals(grade)) return new BigDecimal("6");
    if (UserGrade.V4.getValue().equals(grade)) return new BigDecimal("6.5");
    if (UserGrade.V5.getValue().equals(grade)) return new BigDecimal("7");
    if (UserGrade.V6.getValue().equals(grade)) return new BigDecimal("7.5");
    if (UserGrade.V7.getValue().equals(grade)) return new BigDecimal("8");
    if (UserGrade.V8.getValue().equals(grade)) return new BigDecimal("8.5");
    if (UserGrade.V9.getValue().equals(grade)) return new BigDecimal("9");
    return null;
  }






  private void updateUserIncome(UserInfo userInfo, TradeRecords tradeRecords, BigDecimal surplus_rate) {
    if (tradeRecords == null || surplus_rate == null || surplus_rate.compareTo(new BigDecimal(0)) <= 0)
      return;
    BigDecimal amount = surplus_rate.multiply(BigDecimal.valueOf(tradeRecords.getTrade_amount().doubleValue())).divide(new BigDecimal(10000));

    Double profit_balance = Double.valueOf((userInfo.getProfit_balance() == null) ? 0.0D : userInfo.getProfit_balance().doubleValue());
    Double pristine_amount = profit_balance;
    profit_balance = Double.valueOf(BigDecimal.valueOf(profit_balance.doubleValue()).add(amount).doubleValue());
    Double surplus_amount = profit_balance;

    UserInfo userInfo1 = new UserInfo();
    userInfo1.setId(userInfo.getId());
    userInfo1.setProfit_balance(profit_balance);
    this.sqlDao.update("ape_user_info.update", userInfo1);

    IncomeRecords incomeRecords = new IncomeRecords();
    incomeRecords.setUser_id(userInfo.getId());
    incomeRecords.setMerchant_id(tradeRecords.getMerchant_id());
    incomeRecords.setOrder_no(tradeRecords.getOrder_no());
    incomeRecords.setType(IncomeType.刷卡.getValue());
    incomeRecords.setAmount(Double.valueOf(amount.doubleValue()));
    incomeRecords.setPristine_amount(pristine_amount);
    incomeRecords.setSurplus_amount(surplus_amount);
    incomeRecords.setCreate_time(new Date());
    this.sqlDao.create("ape_income_records.create", incomeRecords);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\job\QuartzJob.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */