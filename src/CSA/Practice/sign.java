package CSA.Practice;

class Sign{
    private String message;
    private int width;
    public Sign(String message, int width){
        this.message = message;
        this.width = width;
    }
    public int numberOfLines(){
        int len = message.length();
        if (len == 0){
            return 0;
        }
        if(len % width == 0){
            return len/width;
        } else{
            return (len/width) + 1;
        }
    }
    public String getLines(){
        String lineForm = "";
        int startPosition = 0;
        if(message.length() == 0){
            return null;
        }
        for(int i = 1; i <= numberOfLines(); i++){
            if(i == numberOfLines()){
                lineForm = lineForm + message.substring(startPosition);
                break;
            }
            lineForm = lineForm + message.substring(startPosition, i * width);
            startPosition += width;
            lineForm = lineForm + ";";
        }
        return lineForm;
    }
}
