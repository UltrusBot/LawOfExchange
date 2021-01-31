package io.github.ultrusbot.lawofexchange.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.ultrusbot.lawofexchange.emc.EmcController;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class setEMCCommand{
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("setemc").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermissionLevel(2);
        }).then((CommandManager.argument("item", ItemStackArgumentType.itemStack()).executes((commandContext) -> {
            return execute(commandContext.getSource(), ItemStackArgumentType.getItemStackArgument(commandContext, "item"), 1);
        }).then(CommandManager.argument("emc", IntegerArgumentType.integer(1)).executes((commandContext) -> {
            return execute(commandContext.getSource(), ItemStackArgumentType.getItemStackArgument(commandContext, "item"), IntegerArgumentType.getInteger(commandContext, "emc"));
        })))));
    }
    private static int execute(ServerCommandSource source, ItemStackArgument item, int emc) throws CommandSyntaxException {
        EmcController.addEMCValue(item.getItem(), emc);
//        source.sendFeedback(new TranslatableText("lawofexchange.command.setemc.success", new TranslatableText("text.lawofexchange.emc"), , emc), true);
        return 1;
    }
}
