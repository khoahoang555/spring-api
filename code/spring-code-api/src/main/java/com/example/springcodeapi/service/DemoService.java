package com.example.springcodeapi.service;

import com.example.springcodeapi.domain.Demo;
import com.example.springcodeapi.repository.IDemoRepository;
import com.example.springcodeapi.service.dto.DemoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DemoService {
    private IDemoRepository demoRepository;

    public DemoService(IDemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public Demo createDemo(DemoDTO demoDTO) {
        Demo demo = new Demo();
        demo.setName(demoDTO.getName());
        //demo.setSeqId(demoRepository.getNextSeqId());
        Demo demoSaved = demoRepository.save(demo);
        return demoSaved;
    }
}
