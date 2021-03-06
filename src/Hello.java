import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Hello {
  public static void main( String[] args) throws Exception 
  {
    HelloLexer lexer = new HelloLexer( new ANTLRFileStream(args[0]));
    CommonTokenStream tokens = new CommonTokenStream( lexer );
    HelloParser parser = new HelloParser( tokens );
    ParseTree tree = parser.r();
    ParseTreeWalker.DEFAULT.walk( new HelloWalker(), tree );
  }
}
