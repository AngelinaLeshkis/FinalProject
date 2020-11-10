//package com.leverx.dealerstat.entity;
//
//import org.springframework.data.redis.core.RedisHash;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Calendar;
//import java.util.Date;
//
//@RedisHash(value = "USER", timeToLive = 200)
//public class VerificationToken implements Serializable {
//
//    private String id;
//    private LocalDateTime timestamp;
//    private String otp;
//
//    public VerificationToken() {
//    }
//
//    public VerificationToken(String id, LocalDateTime timestamp, String otp) {
//        this.id = id;
//        this.timestamp = timestamp;
//        this.otp = otp;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getOtp() {
//        return otp;
//    }
//
//    public void setOtp(String otp) {
//        this.otp = otp;
//    }
//}
