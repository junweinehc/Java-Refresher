package assignment4;

import algs4.*;
import week4inclass.LinkedListStack;

public class ParserXML {
   
    private static boolean isValid(char c){
        return c=='-'||Character.isAlphabetic(c)||Character.isDigit(c);
    }

public static boolean isTag(String token) {
if (token.charAt(0) == '<' && token.charAt(token.length() - 1) == '>') {
                    if (token.charAt(1)!='/'&&!isValid(token.charAt(1))){
                        return false;
                    }
                    String subString=token.substring(2, token.length()-2);
                    for (int i=0; i<subString.length(); i++){
                        if(!isValid(subString.charAt(i))){
                            return false;
                        }
                    }
                    return true;
} else {
return false;
}
}

public static boolean isWord(String token) {
return !isTag(token);
}

public static boolean isClosingTag(String token) {
                if(!isTag(token)){
                    return false;
                }
if (token.charAt(1) == '/') {
return true;
} else {
return false;
}
}

public static boolean isOpeningTag(String token) {
                if(!isTag(token)){
                    return false;
                }
if (token.charAt(1) != '/') {
return true;
} else {
return false;
}
}

public static String tagName(String token) {
if (isTag(token)) {
if (isClosingTag(token))
return token.substring(2, token.length() - 1);
else
return token.substring(1, token.length() - 1);
}
return "";
}

public static void main(String[] args) {
            StdOut.print("Please enter XML file path: ");
            String filename = StdIn.readLine();
            StdIn.fromFile(filename);
            String XMLString = StdIn.readAll();
            String[] tokens = XMLString.split("\\s+");
            parseXML(tokens);
            }
       
        public static void parseXML(String[] tokens){
            LinkedListStack<String> tagNameStack=new LinkedListStack<>();
            for (String token : tokens){
                System.out.println(token);
                if (isOpeningTag(token)){
                    tagNameStack.push(token);
                } else if (isClosingTag(token)){
                    if (tagNameStack.isEmpty()){
                        System.out.println("Error!  There was a closing tag "
                                + "found with no matching opening tag");
                        return;
                    }
                    String tag1=tagNameStack.pop();
                    String tagName1=tagName(tag1);
                    String tagName2=tagName(token);
                    if (!tagName1.equals(tagName2)){
                        System.out.println("Error!  Closing tag did not match its "
                            + "opening tag.  Here is the tag: "+token);
                        return;
                    }
                }
            }
            if(!tagNameStack.isEmpty()) {
                System.out.println("There are opening tags with no matching "
                        + "closing tags.  Here they are: ");
                while(!tagNameStack.isEmpty()){
                    String openingTag=tagNameStack.pop();
                    System.out.println(openingTag);
                }
            } else {
                System.out.println("The XML is correct.");
            }
           
        }
}