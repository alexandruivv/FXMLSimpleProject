package service;

import domain.Status;
import domain.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service{

    private List<Task> taskList;


    private void populateTaskList(){
        taskList.add(new Task(1, Status.TODO, "den1", "utiliz1"));
        taskList.add(new Task(2, Status.INPROGRESS, "den2", "utiliz2"));
        taskList.add(new Task(3, Status.TODO, "den3", "utiliz3"));
        taskList.add(new Task(4, Status.DONE, "den4", "utiliz4"));
        taskList.add(new Task(5, Status.DONE, "den5", "utiliz5"));
        taskList.add(new Task(6, Status.DONE, "den6", "utiliz6"));
        taskList.add(new Task(7, Status.DONE, "den7", "utiliz7"));
        taskList.add(new Task(8, Status.DONE, "den8", "utiliz8"));
        taskList.add(new Task(9, Status.TODO, "den9", "utiliz9"));
        taskList.add(new Task(10, Status.INPROGRESS, "den10", "utiliz10"));
    }

    public Service(){
        taskList = new ArrayList<>();
        populateTaskList();
    }

    private List<Task> filterAndSorter(List<Task> list, Predicate<Task> predicate, Comparator<Task> comparator){
        List<Task> filteredAndSorted = new ArrayList<>();
        filteredAndSorted = list.stream().filter(predicate).sorted(comparator).collect(Collectors.toList());
        return filteredAndSorted;
    }

    public List<Task> filterByTaskStatus(Status status){
        return filterAndSorter(taskList,
                (x)->x.getStatus() == status,
                (x,y)->{
                if(Objects.equals(x.getUtilizator(), y.getUtilizator()))
                    return y.getDenumire().compareTo(x.getDenumire());
                else
                    return y.getUtilizator().compareTo(x.getUtilizator());
                });
    }

    public List<Task> filterByStatusAndUser(Status status, String user){
        return filterAndSorter(taskList,
                (x)->x.getStatus() == status && x.getUtilizator().equals(user),
                (x,y)->y.getDenumire().compareTo(x.getDenumire()));
    }

    public List<Task> filterByUser(String user){
        return filterAndSorter(taskList,
                (x)->x.getUtilizator().equals(user),
                (x,y)->y.getStatus().compareTo(x.getStatus()));
    }

    public List<Task> getAll(){
        return this.taskList;
    }

    public List<Task> getAllTasks(){
        return StreamSupport.stream(getAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
