package com.cx.login.common;

import java.util.Scanner;

import com.cx.login.entity.common.LoginStatusEnum;

public class EnumTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        System.out.println(LoginStatusEnum.prase(a));
        System.out.println(LoginStatusEnum.ACCOUNT_NULL.value() + "" + LoginStatusEnum.ACCOUNT_NULL);
        sc.close();
    }
}
