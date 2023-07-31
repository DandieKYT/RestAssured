package models;

import lombok.Data;

@Data
public class LoginModel {
  String  email, password, token;
}
