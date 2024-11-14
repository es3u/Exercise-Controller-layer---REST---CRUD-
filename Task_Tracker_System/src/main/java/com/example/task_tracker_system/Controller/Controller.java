package com.example.task_tracker_system.Controller;

import com.example.task_tracker_system.ApiRecaponse.Recaponse;
import com.example.task_tracker_system.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

////////////port 1010
@RestController
@RequestMapping("/api/v1/tracker")
public class Controller {

    ArrayList<Task> tasks = new ArrayList<>();


@GetMapping("/display")
public ArrayList<Task> Display(){

    return tasks;
}

@PostMapping("/add")
public Recaponse addTask(@RequestBody Task task){

    tasks.add(task);
    return new Recaponse("Added Task is successfully");
}

@PutMapping("/update")
public Recaponse updateTask(@RequestBody Task task){
    int index = tasks.indexOf(task);
    tasks.set(index, task);
    return new Recaponse("Updated Task is successfully");
}

@DeleteMapping(("/delete/{id}"))
public Recaponse DeleteTask(@PathVariable int id){
    tasks.remove(id);
    return new Recaponse("delete is successful");
}

@PutMapping("/change/{index}")
public String changeStatus(@PathVariable int index){
    if(tasks.get(index).getStatus().contains("not done")){
        tasks.get(index).setStatus("done");
        return "Status is updated successfully";
    }else{
        return "Status is done";}

}


@GetMapping("/search/{title}")
public ArrayList<Task> search(@PathVariable String title) {
    ArrayList<Task> t1 = new ArrayList<>();
    for (Task t : tasks) {
        if (t.getTitle().contains(title)) {
            t1.add(t);

        }

    }
    return t1;
}






}
