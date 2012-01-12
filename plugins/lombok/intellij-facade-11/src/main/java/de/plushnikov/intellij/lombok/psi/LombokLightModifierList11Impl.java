package de.plushnikov.intellij.lombok.psi;

import com.intellij.lang.Language;
import com.intellij.psi.Modifier;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.impl.light.LightModifierList;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Plushnikov Michail
 */
public class LombokLightModifierList11Impl extends LightModifierList {
  private static final Set<String> ALL_MODIFIERS = new HashSet<String>(Arrays.asList(PsiModifier.MODIFIERS));

  public LombokLightModifierList11Impl(PsiManager manager, final Language language, String... modifiers) {
    super(manager, language, modifiers);
  }

  public void setModifierProperty(@Modifier @NotNull @NonNls String name, boolean value) throws IncorrectOperationException {
    if (value) {
      addModifier(name);
    } else {
      if (hasModifierProperty(name)) {
        removeModifier(name);
      }
    }
  }

  private void removeModifier(@Modifier @NotNull @NonNls String name) {
    final Collection<String> myModifiers = collectAllModifiers();
    myModifiers.remove(name);

    clearModifiers();

    for (String modifier : myModifiers) {
      addModifier(modifier);
    }
  }

  private Collection<String> collectAllModifiers() {
    Collection<String> result = new HashSet<String>();
    for (@Modifier String modifier : ALL_MODIFIERS) {
      if (hasModifierProperty(modifier)) {
        result.add(modifier);
      }
    }
    return result;
  }

  public void checkSetModifierProperty(@Modifier @NotNull @NonNls String name, boolean value) throws IncorrectOperationException {
    throw new IncorrectOperationException();
  }

  public String toString() {
    return "LombokLightModifierList11Impl";
  }
}
