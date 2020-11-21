package com.educandoweb.couse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.entities.Hierarquia;
import com.educandoweb.couse.entities.Transferencia;
import com.educandoweb.couse.repositores.FuncionarioRepository;
import com.educandoweb.couse.repositores.HierarquiaRepository;
import com.educandoweb.couse.repositores.TransferenciaRepository;
import com.educandoweb.couse.services.exceptions.NaoPodeTransferirParaComiteHierarquiaException;



@Service
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository repository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private HierarquiaRepository hierarquiaRepository;


	public List<Transferencia> findAll(){
		return repository.findAll();	

	}
	
public Transferencia transferirFuncionario(Transferencia obj) {
	
	Funcionario objFunc = new Funcionario();
	objFunc = funcionarioRepository.findByCpf(obj.getCpf());
	// ATUALIZAR A FUNÇÃO 
	if (obj.getFuncaoDestino() != null) {
		objFunc.setFuncao(obj.getFuncaoDestino());
		funcionarioRepository.save(objFunc);
	} 
	// QUANDO ELE É COORDENADOR 
	if (objFunc.getCoordenador() == 1) {
		
		if (obj.isTrocaDeCadeiras() == true) { // É UMA TROCA DE COMITE DE CADEIRAS
			Comite comiteDestino = obj.getComiteDestino();
			Comite comiteOrigem = objFunc.getComite();
			
			objFunc.setComite(comiteDestino);
			
			
			Hierarquia objHierarquia = new Hierarquia();
			objHierarquia = hierarquiaRepository.findByComiteAndRelacionamento(obj.getComiteDestino().getId(), 'c');

			
			Funcionario objFunc2 = new Funcionario();
			objFunc2 = funcionarioRepository.findByCpf(objHierarquia.getFuncionario());
			
			objFunc2.setComite(comiteOrigem);
			
			Hierarquia objHierarquia2 = new Hierarquia();
			objHierarquia2 = hierarquiaRepository.findByComiteAndRelacionamento(comiteOrigem.getId(), 'c');
			
			objHierarquia.setFuncionario(obj.getCpf());
			objHierarquia2.setFuncionario(objFunc2.getCpf());
			
			
			funcionarioRepository.save(objFunc);
			funcionarioRepository.save(objFunc2);
			
			hierarquiaRepository.save(objHierarquia);
			hierarquiaRepository.save(objHierarquia2);
		

			
		} else { // NÃO É UMA TROCA DE CADEIRAS
			throw new NaoPodeTransferirParaComiteHierarquiaException();
		
			
		}
		
		
	} else {
	// NAO É COORDENADOR
		
	}
	
	
	
	
	
	
	
	return null;
	}
}
