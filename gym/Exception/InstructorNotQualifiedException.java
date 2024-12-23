package gym.Exception;

public class InstructorNotQualifiedException extends Exception {
  private static final String DEFAULT_MESSAGE = "Error: Instructor is not qualified to conduct this session type.";
  public InstructorNotQualifiedException() {
    super(DEFAULT_MESSAGE);
  }
}
