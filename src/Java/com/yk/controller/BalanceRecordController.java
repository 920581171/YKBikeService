package com.yk.controller;

import com.yk.Utils.GsonUtils;
import com.yk.impl.BalanceRecordServiceImpl;
import com.yk.impl.UserInfoServiceImpl;
import com.yk.pojo.BalanceRecord;
import com.yk.pojo.BikeRecord;
import com.yk.pojo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(description = "余额信息相关")
@Controller
@RequestMapping(value = "/balancerecord")
public class BalanceRecordController {
    @Autowired
    BalanceRecordServiceImpl balanceRecordService;
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ResponseBody
    @ApiOperation(value = "根据用户Id查询余额记录", httpMethod = "POST")
    @RequestMapping(value = "/findBalanceRecordByUserId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findBalanceRecordByUserId(@RequestParam("userId") String userId) {
        try {
            List<BalanceRecord> balanceRecords = balanceRecordService.searchBalanceRecordByUserId(userId);
            return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有余额记录", httpMethod = "POST")
    @RequestMapping(value = "/findAllBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllBalanceRecord() {
        try {
            List<BalanceRecord> balanceRecords = balanceRecordService.searchAllBalanceRecord();
            return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ApiOperation(value = "根据时间范围查询所有余额记录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findAllDateBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String findAllDateBalanceRecord(@RequestParam("startTime")String startTime,@RequestParam("endTime") String endTime) {
        try {
            DateFormat format = SimpleDateFormat.getDateInstance();
            List<BalanceRecord> balanceRecords = balanceRecordService.searchAllDateBalanceRecord(format.parse(startTime),format.parse(endTime));
            return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查询所有余额记录", httpMethod = "POST")
    @RequestMapping(value = "/queryPageBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryPageBalanceRecord(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize) {
        try {
            List<BalanceRecord> balanceRecords = balanceRecordService.queryPageBalanceRecord(pageIndex,pageSize);
            return GsonUtils.responseObjectJson(balanceRecords != null, balanceRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加余额记录", httpMethod = "POST")
    @RequestMapping(value = "/addBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addBalanceRecord(@RequestParam("userId") String userId, @RequestParam("balance") float balance) {
        try {
            UserInfo userInfo = userInfoService.searchUserId(userId);
            if (userInfo == null)
                return GsonUtils.responseErrorJson();

            userInfo.setBalance(userInfo.getBalance() + balance);
            boolean b = userInfoService.updateUserInfo(userInfo) > 0;

            if (!b)
                return GsonUtils.responseErrorJson();

            BalanceRecord balanceRecord = new BalanceRecord().setUserId(userId)
                    .setBalance(balance)
                    .setCreateTime(new Date(System.currentTimeMillis()))
                    .setIsExchange("0");

            return GsonUtils.responseSimpleJson(balanceRecordService.addBalanceRecord(balanceRecord) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新余额记录", httpMethod = "POST")
    @RequestMapping(value = "/updateBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateBalanceRecord(@RequestParam("recordId") String recordId) {
        try {
            BalanceRecord balanceRecord = balanceRecordService.searchBalanceRecordId(recordId);
            return GsonUtils.responseObjectJson(balanceRecordService.updateBalanceRecord(balanceRecord) > 0, balanceRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除余额记录", httpMethod = "POST")
    @RequestMapping(value = "/deleteBalanceRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deleteBalanceRecord(@RequestParam("recordId") String recordId) {
        try {
            return GsonUtils.responseSimpleJson(balanceRecordService.deleteBalanceRecord(new BalanceRecord().setRecordId(recordId)) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.responseErrorJson();
        }
    }
}
