package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.Subdistrito;
import br.gov.prefeitura.mimg.repository.subdistrito.SubdistritoRepositoryQuery;

public interface SubdistritoRepository extends JpaRepository<Subdistrito, Integer> , SubdistritoRepositoryQuery  {

}
