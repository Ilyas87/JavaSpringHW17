package org.itstep.jpa.controllers;

import org.itstep.jpa.entities.Student;
import org.itstep.jpa.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final StudentService service;

    public HomeController(final StudentService service){
        this.service = service;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        List<Student> students = service.getAllStudent();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping(value = "/search/{id}")
    public String getStudent(@PathVariable("id") Long id, Model model) {
        Student student = service.getStudent(id);
        model.addAttribute("student", student);
        return "student";
    }

    @PostMapping(value = "add-student")
    public String addStudent(@RequestParam(name = "name", defaultValue = "Alan") String name, @RequestParam(name = "age", defaultValue = "18") Integer age) {
        service.addStudent(new Student(null, name, age));

        return "redirect:/";
    }

    @GetMapping(value = "/update/{id}")
    public String updateStudent(Model model, @PathVariable("id") Long id){
        Student student = service.getStudent(id);
        model.addAttribute("student", student);
        return "update";
    }

    @PutMapping(value = "/update/{id}/edit")
    public String updateStudent(@PathVariable("id") Long id, @RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age){
        Student student = service.getStudent(id);
        student.setName(name);
        student.setAge(age);
        service.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteStudent(Model model, @PathVariable("id") Long id){
        Student student = service.getStudent(id);
        model.addAttribute("student", student);
        return "delete";
    }

    @DeleteMapping(value = "/delete/{id}/remove")
    public String deleteStudent(@PathVariable("id") Long id){
        service.deleteStudent(id);
        return "redirect:/";
    }
}
