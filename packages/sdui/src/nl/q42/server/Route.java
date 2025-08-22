package nl.q42.server;

public abstract class Route implements RequestHandler<OrderHandler.Order, String> {
    @Override
    public String handleRequest(Order event, Context context) {}
}