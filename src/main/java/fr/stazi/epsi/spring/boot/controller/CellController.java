package fr.stazi.epsi.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.exception.AlreadyExistsException;
import fr.stazi.epsi.spring.boot.repository.CellRepository;
import fr.stazi.epsi.spring.boot.service.CellService;

@RestController
@RequestMapping("/api/cell")
public class CellController {

	@Autowired
	private CellRepository cellRepo;

	private CellService cellService;

	public CellController(CellService cellService, CellRepository cellRepo) {
		super();
		this.cellService = cellService;
		this.cellRepo = cellRepo;
	}
	
	@GetMapping
	public List<Cell> getCells() {
		return cellRepo.findAll();
	}
	
	@PreAuthorize("hasAuthority('CELL_CREATE')")
	@PostMapping
	public Cell createCell(@RequestBody Cell cell) throws AlreadyExistsException {
		if (cell.getId() == null) {
			return cellRepo.save(cell);
		} 
		
		throw new AlreadyExistsException();
	}
	@PreAuthorize("@securityMethodService.canManage(#id, principal)")
	@PutMapping("/{id}")
	public Cell update(@PathVariable Long id, @RequestBody Cell entity) {
		return cellService.update(entity);
	}
	
	

}
