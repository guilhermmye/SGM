package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Subdistrito;
import br.gov.prefeitura.msc.repository.subdistrito.SubdistritoRepositoryQuery;

public interface SubdistritoRepository extends JpaRepository<Subdistrito, Integer> , SubdistritoRepositoryQuery  {

}
