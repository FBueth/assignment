package assignment;


class UserInput {
    private static String helpFlag = "-h";
    private static String fileFormat = ".log";
    private static String helpMessage = "How to use this program:\n" +
            "Please provide the filename of a log including the file ending (e.g. 'timing.log') from the current directory as well as a number.\n" +
            "The program will print out three things:\n" +
            "1) The resources with the highest average request duration, limited by the provided number.\n" +
            "2) A histogram of the hourly number of requests.\n" +
            "3) The number of milliseconds the program ran.";
    private static String invalidInputMessage = "Invalid input. Please check your input or type '-h' for instructions on how to use this program.";

    static boolean isValidUserInput(String[] arguments) {
        boolean argumentsValid = false;

        switch (arguments.length) {
            case 1:
                if (isHelpFlag(arguments[0])) {
                    System.out.println(helpMessage);
                } else {
                    System.out.println(invalidInputMessage);
                }
                break;
            case 2:
                if (isValidLogfile(arguments[0]) && isValidNumber(arguments[1])) {
                    argumentsValid = true;
                } else {
                    System.out.println(invalidInputMessage);
                }
                break;
            default:
                System.out.println(invalidInputMessage);
                break;
        }
        return argumentsValid;
    }

    private static boolean isHelpFlag(String argument) {
        return (argument.equals(helpFlag));
    }

    private static boolean isValidLogfile(String fileName) {
        return fileName.endsWith(fileFormat);
    }

    private static boolean isValidNumber(String numberAsString) {
        try {
            Integer.parseInt(numberAsString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
