package com.zgy.model_design.guarded_and_state;

public class HavingPeopleState extends State{
    private Room room;

    public HavingPeopleState(Room room){
        this.room = room;
    }

    @Override
    public void onEmpty() {
        room.changeState(new EmptyState(room));
        System.out.println("∑øº‰ø’¡À°£°£°£");
    }

    @Override
    public void onHavingPeople() {
        room.changeState(new HavingPeopleState(room));
    }
}
