package com.abridged.forestrymanagementsystem.dao;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Contract;


public interface ContractDao {
	public Contract getContract(String contractNumber);

	public boolean addContract(Contract contract);

	public boolean updateContract(Contract contract);

	public boolean deleteContract(String contractNumber);
	
	public List<Contract> getAllContracts();
}
