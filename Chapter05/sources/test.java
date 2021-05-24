public interface Frame {
    FrameDescriptor getFrameDescriptor();

    Object[] getArguments();

    boolean isType(FrameSlot slot);

    Type getType(FrameSlot slot) throws FrameSlotTypeException;

    void setType(FrameSlot slot, Type value);

    Object getValue(FrameSlot slot);

    MaterializedFrame materialize();
}