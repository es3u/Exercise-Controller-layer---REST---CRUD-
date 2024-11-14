package com.example.bankmanagement.Controller;
import com.example.bankmanagement.Model.BankModel;
import com.example.bankmanagement.Recapnse.Recaponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//////////port is 1011
@RestController
@RequestMapping("/api/v1/bank")
public class Customers {
    ArrayList<BankModel> customers = new ArrayList<>();

    @GetMapping("/display")
    public  ArrayList<BankModel> getCustomers() {
        return customers;
    }
    @PostMapping("/add")
    public Recaponse addCustomer(@RequestBody BankModel customer) {
        customers.add(customer);
        return new Recaponse("added customer successfully");
    }

    @PutMapping("/update/{index}")
    public Recaponse updateCustomer(@PathVariable int index ,@RequestBody BankModel customer) {
        customers.set(index ,customer);
        return new Recaponse("updated customer successfully");
    }

    @DeleteMapping ("/delete/{index}")
    public Recaponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);
 return new Recaponse("deleted customer successfully");
    }

    @PutMapping("/deposit/{d}/{id}")
    public Recaponse DepositMoney(@PathVariable double d , @PathVariable String id) {
        try {
                if(d<=0)
                     throw new Exception() ;
            for (BankModel customer : customers) {
                if (customer.getId().equals(id)) {
                    customer.setBalance(customer.getBalance() + d);
                    return new Recaponse("deposited money successfully");
                }
            }


        }catch (Exception e){
            return new Recaponse("\"deposit money failed\"") ;
        }

            return new Recaponse("deposited money fail");
    }

    @PutMapping("/withdraw/{id}/{d}")
    public Recaponse Withdraw_money(@PathVariable double d , @PathVariable String id) {




        try {
            if(d<=0)
                throw new Exception() ;
            for (BankModel customer : customers) {
                if (customer.getId().equals(id)) {
                    if(customer.getBalance()<=0 && customer.getBalance()-d <0){
                        return new Recaponse("\"withdraw money failed\"") ;
                    }else{
                    customer.setBalance(customer.getBalance() - d);
                    return new Recaponse("withdraw money successfully");}
                }
            }


        }catch (Exception e){
            return new Recaponse("\"withdraw money failed\"") ;
        }

        return new Recaponse("withdraw money fail");
    }

    @GetMapping("/balance/{id}")
    public double getbalance(@PathVariable String id) {
       ArrayList<BankModel> c =new ArrayList<>();
        for (BankModel customer : customers) {
            if (customer.getId().equals(id)) {
                c.add(customer);
            }
        }

        return c.get(c.size()-1).getBalance();

    }




    }

