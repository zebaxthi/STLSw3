package com.uco.stloan.Repository;

import com.uco.stloan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    @Query(value = "SELECT * FROM LOAN WHERE REF=?",nativeQuery = true )
    public Loan findLoanAticlesByUser(String identification);

}
