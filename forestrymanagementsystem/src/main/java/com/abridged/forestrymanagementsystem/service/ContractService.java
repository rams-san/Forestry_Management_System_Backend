package com.abridged.forestrymanagementsystem.service;

import java.util.List;

import com.abridged.forestrymanagementsystem.dto.Contract;
import com.abridged.forestrymanagementsystem.dto.ContractResponse;

public interface ContractService {
	public Contract serviceGetContract(String contractNumber, ContractResponse contractResponse);

	public boolean serviceAddContract(Contract contract, ContractResponse contractResponse);

	public boolean serviceUpdateContract(Contract contract, ContractResponse contractResponse);

	public boolean serviceDeleteContract(String contractNumber, ContractResponse contractResponse);
	
	public List<Contract> serviceGetAllContracts();
}
