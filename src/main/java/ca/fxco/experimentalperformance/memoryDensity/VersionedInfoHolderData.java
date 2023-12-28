package ca.fxco.experimentalperformance.memoryDensity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VersionedInfoHolderData {

    private final String targetClassName;
    private final final String modId;
    private final boolean defaultValue;

    private final String[] redirectFields;

    public VersionedInfoHolderData(String targetClassName, List<String> redirectFields,
                                   List<InfoHolderPart> holderVersions) {
        this(targetClassName, redirectFields, holderVersions, HolderDataRegistry.MINECRAFT_ID);
    }

    public VersionedInfoHolderData(String targetClassName, List<String> redirectFields,
                                   List<InfoHolderPart> holderVersions, String modId) {
        this(targetClassName, redirectFields, holderVersions, modId, true);
    }

    public VersionedInfoHolderData(String targetClassName, List<String> redirectFields,
                                   List<InfoHolderPart> holderVersions, String modId, boolean defaultValue) {
        this.targetClassName = targetClassName;
        this.redirectFields = redirectFields.toArray(new String[]{}); // Use empty array for type inference
        this.modId = modId;
        this.defaultValue = defaultValue;
    }

    public boolean getDefaultValue() {
        return this.defaultValue;
    }

    public String getTargetClassName() {
        return this.targetClassName;
    }

    public List<String> getRedirectFields() {
        return Collections.unmodifiableList(Arrays.asList(this.redirectFields));
    }

    public String getModId() {
        return this.modId;
    }

    public InfoHolderPart[] getVersionedInfoHolderParts() {
        return holderVersions.toArray(new InfoHolderPart[]{});
    }

    public static InfoHolderPart part(List<String> extraRedirectFields, String versionPredicate) {
        return new InfoHolderPart(versionPredicate, extraRedirectFields);
    }

    public static InfoHolderPart part(String versionPredicate) {
        return new InfoHolderPart(versionPredicate, List.of());
    }

    public record InfoHolderPart(String versionPredicate, List<String> extraRedirectFields) {

        public String getVersionPredicate() {
            return this.versionPredicate;
        }

        public List<String> getExtraRedirectFields() {
            return this.extraRedirectFields;
        }
    }
}
