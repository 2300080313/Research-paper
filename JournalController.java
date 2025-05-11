package com.klu;

import com.researchflow.entity.Journal;
import com.researchflow.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping("/add")
    public ResponseEntity<?> addJournal(@RequestBody Journal journal) {
        Journal saved = journalService.addJournal(journal);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Object> getAllJournals() {
        return ResponseEntity.ok(journalService.getAllJournals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable Long id) {
        Optional<Journal> journalOpt = journalService.getJournalById(id);
        return journalOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
