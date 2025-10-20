package poly.edu.service;

import poly.edu.entity.Account;
import java.util.Optional;

public interface AccountService {
    Account findById(String username);
}
