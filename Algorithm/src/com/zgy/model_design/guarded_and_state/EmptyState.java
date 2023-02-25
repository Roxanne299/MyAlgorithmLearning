package com.zgy.model_design.guarded_and_state;

public class EmptyState extends State{
    private Room room;
    public EmptyState(Room room){
        this.room = room;
    }

    @Override
    public void onEmpty() {
        room.changeState(new EmptyState(room));
        System.out.print("����׼����");

    }

    @Override
    public void onHavingPeople() {
        room.changeState(new HavingPeopleState(room));
        System.out.println("��������...");

    }
}
