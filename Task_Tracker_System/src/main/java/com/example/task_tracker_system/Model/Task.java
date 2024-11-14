package com.example.task_tracker_system.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private final int id;
    private  String title;
    private  String description;
    private  String status;




}
