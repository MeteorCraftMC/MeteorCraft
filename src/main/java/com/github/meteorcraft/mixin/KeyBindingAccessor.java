package com.github.meteorcraft.mixin;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(KeyBinding.class)
public interface KeyBindingAccessor {
    @Accessor
    static Map<InputUtil.Key, KeyBinding> getKEY_TO_BINDINGS() {
        throw new UnsupportedOperationException();
    }
}
