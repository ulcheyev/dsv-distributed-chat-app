// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatService.proto

package generated;

/**
 * Protobuf type {@code generated.PermissionRequest}
 */
public final class PermissionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:generated.PermissionRequest)
    PermissionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PermissionRequest.newBuilder() to construct.
  private PermissionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PermissionRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PermissionRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PermissionRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            generated.Remote.Builder subBuilder = null;
            if (remote_ != null) {
              subBuilder = remote_.toBuilder();
            }
            remote_ = input.readMessage(generated.Remote.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(remote_);
              remote_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {

            clock_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return generated.ChatService.internal_static_generated_PermissionRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.ChatService.internal_static_generated_PermissionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.PermissionRequest.class, generated.PermissionRequest.Builder.class);
  }

  public static final int REMOTE_FIELD_NUMBER = 1;
  private generated.Remote remote_;
  /**
   * <code>.generated.Remote remote = 1;</code>
   * @return Whether the remote field is set.
   */
  @java.lang.Override
  public boolean hasRemote() {
    return remote_ != null;
  }
  /**
   * <code>.generated.Remote remote = 1;</code>
   * @return The remote.
   */
  @java.lang.Override
  public generated.Remote getRemote() {
    return remote_ == null ? generated.Remote.getDefaultInstance() : remote_;
  }
  /**
   * <code>.generated.Remote remote = 1;</code>
   */
  @java.lang.Override
  public generated.RemoteOrBuilder getRemoteOrBuilder() {
    return getRemote();
  }

  public static final int CLOCK_FIELD_NUMBER = 2;
  private int clock_;
  /**
   * <code>int32 clock = 2;</code>
   * @return The clock.
   */
  @java.lang.Override
  public int getClock() {
    return clock_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (remote_ != null) {
      output.writeMessage(1, getRemote());
    }
    if (clock_ != 0) {
      output.writeInt32(2, clock_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (remote_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRemote());
    }
    if (clock_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, clock_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof generated.PermissionRequest)) {
      return super.equals(obj);
    }
    generated.PermissionRequest other = (generated.PermissionRequest) obj;

    if (hasRemote() != other.hasRemote()) return false;
    if (hasRemote()) {
      if (!getRemote()
          .equals(other.getRemote())) return false;
    }
    if (getClock()
        != other.getClock()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasRemote()) {
      hash = (37 * hash) + REMOTE_FIELD_NUMBER;
      hash = (53 * hash) + getRemote().hashCode();
    }
    hash = (37 * hash) + CLOCK_FIELD_NUMBER;
    hash = (53 * hash) + getClock();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.PermissionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.PermissionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.PermissionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.PermissionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.PermissionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.PermissionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.PermissionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.PermissionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.PermissionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.PermissionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.PermissionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.PermissionRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(generated.PermissionRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code generated.PermissionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:generated.PermissionRequest)
      generated.PermissionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.ChatService.internal_static_generated_PermissionRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.ChatService.internal_static_generated_PermissionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.PermissionRequest.class, generated.PermissionRequest.Builder.class);
    }

    // Construct using generated.PermissionRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (remoteBuilder_ == null) {
        remote_ = null;
      } else {
        remote_ = null;
        remoteBuilder_ = null;
      }
      clock_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.ChatService.internal_static_generated_PermissionRequest_descriptor;
    }

    @java.lang.Override
    public generated.PermissionRequest getDefaultInstanceForType() {
      return generated.PermissionRequest.getDefaultInstance();
    }

    @java.lang.Override
    public generated.PermissionRequest build() {
      generated.PermissionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.PermissionRequest buildPartial() {
      generated.PermissionRequest result = new generated.PermissionRequest(this);
      if (remoteBuilder_ == null) {
        result.remote_ = remote_;
      } else {
        result.remote_ = remoteBuilder_.build();
      }
      result.clock_ = clock_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof generated.PermissionRequest) {
        return mergeFrom((generated.PermissionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.PermissionRequest other) {
      if (other == generated.PermissionRequest.getDefaultInstance()) return this;
      if (other.hasRemote()) {
        mergeRemote(other.getRemote());
      }
      if (other.getClock() != 0) {
        setClock(other.getClock());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      generated.PermissionRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.PermissionRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private generated.Remote remote_;
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> remoteBuilder_;
    /**
     * <code>.generated.Remote remote = 1;</code>
     * @return Whether the remote field is set.
     */
    public boolean hasRemote() {
      return remoteBuilder_ != null || remote_ != null;
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     * @return The remote.
     */
    public generated.Remote getRemote() {
      if (remoteBuilder_ == null) {
        return remote_ == null ? generated.Remote.getDefaultInstance() : remote_;
      } else {
        return remoteBuilder_.getMessage();
      }
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    public Builder setRemote(generated.Remote value) {
      if (remoteBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        remote_ = value;
        onChanged();
      } else {
        remoteBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    public Builder setRemote(
        generated.Remote.Builder builderForValue) {
      if (remoteBuilder_ == null) {
        remote_ = builderForValue.build();
        onChanged();
      } else {
        remoteBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    public Builder mergeRemote(generated.Remote value) {
      if (remoteBuilder_ == null) {
        if (remote_ != null) {
          remote_ =
            generated.Remote.newBuilder(remote_).mergeFrom(value).buildPartial();
        } else {
          remote_ = value;
        }
        onChanged();
      } else {
        remoteBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    public Builder clearRemote() {
      if (remoteBuilder_ == null) {
        remote_ = null;
        onChanged();
      } else {
        remote_ = null;
        remoteBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    public generated.Remote.Builder getRemoteBuilder() {
      
      onChanged();
      return getRemoteFieldBuilder().getBuilder();
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    public generated.RemoteOrBuilder getRemoteOrBuilder() {
      if (remoteBuilder_ != null) {
        return remoteBuilder_.getMessageOrBuilder();
      } else {
        return remote_ == null ?
            generated.Remote.getDefaultInstance() : remote_;
      }
    }
    /**
     * <code>.generated.Remote remote = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> 
        getRemoteFieldBuilder() {
      if (remoteBuilder_ == null) {
        remoteBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder>(
                getRemote(),
                getParentForChildren(),
                isClean());
        remote_ = null;
      }
      return remoteBuilder_;
    }

    private int clock_ ;
    /**
     * <code>int32 clock = 2;</code>
     * @return The clock.
     */
    @java.lang.Override
    public int getClock() {
      return clock_;
    }
    /**
     * <code>int32 clock = 2;</code>
     * @param value The clock to set.
     * @return This builder for chaining.
     */
    public Builder setClock(int value) {
      
      clock_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 clock = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearClock() {
      
      clock_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:generated.PermissionRequest)
  }

  // @@protoc_insertion_point(class_scope:generated.PermissionRequest)
  private static final generated.PermissionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.PermissionRequest();
  }

  public static generated.PermissionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PermissionRequest>
      PARSER = new com.google.protobuf.AbstractParser<PermissionRequest>() {
    @java.lang.Override
    public PermissionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PermissionRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PermissionRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PermissionRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.PermissionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

