import java.util.Stack;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

class Scope {
    String entry;
    String exit;
    
    public Scope(String entry, String exit) {
        this.entry = entry;
        this.exit = exit;
    }
}


public class HelloWalker extends HelloBaseListener {
  Stack<Scope> scopes = new Stack<Scope>();

  public void enterR(HelloParser.RContext ctx ) {
    System.out.println("Entering R : ");// + ctx.IDX().getText() );
    scopes.push(new Scope("main entry", "main exit"));
  }

  public void exitR(HelloParser.RContext ctx ) {
    System.out.println("Exiting R");
    Scope scope = scopes.peek();
  }
  
  public void enterConditional(HelloParser.ConditionalContext ctx) {
	System.out.println("Entering conditional: " + ctx.BOOL());
	//Interval srcInterval = ctx.getSourceInterval();
	//Token firstToken = TokenStream.get(srcInterval.a);
	//int line = firstToken.getLine();
	Token token = ctx.getStart();
	int lineNo = token.getLine();
	int posInLine = token.getCharPositionInLine();
	System.out.println("Line no " + lineNo + "; col no " + posInLine);
    
    Scope scope = scopes.peek();
    System.out.println(scope.entry + "=>" + ctx.getText());
    
    scopes.push(new Scope(ctx.getText(), "Exit of " + ctx.getText()));
    
    //     Conditional (Exit=E)
    //        /   \
    //       E  Child (Exit=E)
  }
  
  public void leaveConditional(HelloParser.ConditionalContext ctx) {
    scopes.pop();
  }
 
  public void enterAssignment(HelloParser.AssignmentContext ctx) {
	System.out.println("Entering assignment: " + ctx.Identifier(0) + "=" + ctx.Identifier(1));
    
    Scope scope = scopes.peek();
    System.out.println(scope.entry + "=>" + ctx.getText());
    
    scopes.push(new Scope(ctx.getText(), "Exit of " + ctx.getText()));
  }
  
  public void leaveAssignment(HelloParser.AssignmentContext ctx) {
      scopes.pop();
  }
  
  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    System.out.println("Entering rule at line: " + ctx.getStart().getLine());
  }
}
