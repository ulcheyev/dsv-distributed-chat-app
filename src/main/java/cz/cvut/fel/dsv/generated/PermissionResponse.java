// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatService.proto

package generated;

/**
 * Protobuf type {@code generated.PermissionResponse}
 */
public final class PermissionResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:generated.PermissionResponse)
    PermissionResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PermissionResponse.newBuilder() to construct.
  private PermissionResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PermissionResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PermissionResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PermissionResponse(
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
          case 18: {
            generated.Remote.Builder subBuilder = null;
            if (responseByRemote_ != null) {
              subBuilder = responseByRemote_.toBuilder();
            }
            responseByRemote_ = input.readMessage(generated.Remote.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(responseByRemote_);
              responseByRemote_ = subBuilder.buildPartial();
            }

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
    return generated.ChatService.internal_static_generated_PermissionResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.ChatService.internal_static_generated_PermissionResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.PermissionResponse.class, generated.PermissionResponse.Builder.class);
  }

  public static final int RESPONSEBYREMOTE_FIELD_NUMBER = 2;
  private generated.Remote responseByRemote_;
  /**
   * <code>.generated.Remote responseByRemote = 2;</code>
   * @return Whether the responseByRemote field is set.
   */
  @java.lang.Override
  public boolean hasResponseByRemote() {
    return responseByRemote_ != null;
  }
  /**
   * <code>.generated.Remote responseByRemote = 2;</code>
   * @return The responseByRemote.
   */
  @java.lang.Override
  public generated.Remote getResponseByRemote() {
    return responseByRemote_ == null ? generated.Remote.getDefaultInstance() : responseByRemote_;
  }
  /**
   * <code>.generated.Remote responseByRemote = 2;</code>
   */
  @java.lang.Override
  public generated.RemoteOrBuilder getResponseByRemoteOrBuilder() {
    return getResponseByRemote();
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
    if (responseByRemote_ != null) {
      output.writeMessage(2, getResponseByRemote());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (responseByRemote_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getResponseByRemote());
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
    if (!(obj instanceof generated.PermissionResponse)) {
      return super.equals(obj);
    }
    generated.PermissionResponse other = (generated.PermissionResponse) obj;

    if (hasResponseByRemote() != other.hasResponseByRemote()) return false;
    if (hasResponseByRemote()) {
      if (!getResponseByRemote()
          .equals(other.getResponseByRemote())) return false;
    }
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
    if (hasResponseByRemote()) {
      hash = (37 * hash) + RESPONSEBYREMOTE_FIELD_NUMBER;
      hash = (53 * hash) + getResponseByRemote().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.PermissionResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.PermissionResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.PermissionResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.PermissionResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.PermissionResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.PermissionResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.PermissionResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.PermissionResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.PermissionResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.PermissionResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.PermissionResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.PermissionResponse parseFrom(
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
  public static Builder newBuilder(generated.PermissionResponse prototype) {
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
   * Protobuf type {@code generated.PermissionResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:generated.PermissionResponse)
      generated.PermissionResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.ChatService.internal_static_generated_PermissionResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.ChatService.internal_static_generated_PermissionResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.PermissionResponse.class, generated.PermissionResponse.Builder.class);
    }

    // Construct using generated.PermissionResponse.newBuilder()
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
      if (responseByRemoteBuilder_ == null) {
        responseByRemote_ = null;
      } else {
        responseByRemote_ = null;
        responseByRemoteBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.ChatService.internal_static_generated_PermissionResponse_descriptor;
    }

    @java.lang.Override
    public generated.PermissionResponse getDefaultInstanceForType() {
      return generated.PermissionResponse.getDefaultInstance();
    }

    @java.lang.Override
    public generated.PermissionResponse build() {
      generated.PermissionResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.PermissionResponse buildPartial() {
      generated.PermissionResponse result = new generated.PermissionResponse(this);
      if (responseByRemoteBuilder_ == null) {
        result.responseByRemote_ = responseByRemote_;
      } else {
        result.responseByRemote_ = responseByRemoteBuilder_.build();
      }
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
      if (other instanceof generated.PermissionResponse) {
        return mergeFrom((generated.PermissionResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.PermissionResponse other) {
      if (other == generated.PermissionResponse.getDefaultInstance()) return this;
      if (other.hasResponseByRemote()) {
        mergeResponseByRemote(other.getResponseByRemote());
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
      generated.PermissionResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.PermissionResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private generated.Remote responseByRemote_;
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> responseByRemoteBuilder_;
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     * @return Whether the responseByRemote field is set.
     */
    public boolean hasResponseByRemote() {
      return responseByRemoteBuilder_ != null || responseByRemote_ != null;
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     * @return The responseByRemote.
     */
    public generated.Remote getResponseByRemote() {
      if (responseByRemoteBuilder_ == null) {
        return responseByRemote_ == null ? generated.Remote.getDefaultInstance() : responseByRemote_;
      } else {
        return responseByRemoteBuilder_.getMessage();
      }
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    public Builder setResponseByRemote(generated.Remote value) {
      if (responseByRemoteBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        responseByRemote_ = value;
        onChanged();
      } else {
        responseByRemoteBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    public Builder setResponseByRemote(
        generated.Remote.Builder builderForValue) {
      if (responseByRemoteBuilder_ == null) {
        responseByRemote_ = builderForValue.build();
        onChanged();
      } else {
        responseByRemoteBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    public Builder mergeResponseByRemote(generated.Remote value) {
      if (responseByRemoteBuilder_ == null) {
        if (responseByRemote_ != null) {
          responseByRemote_ =
            generated.Remote.newBuilder(responseByRemote_).mergeFrom(value).buildPartial();
        } else {
          responseByRemote_ = value;
        }
        onChanged();
      } else {
        responseByRemoteBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    public Builder clearResponseByRemote() {
      if (responseByRemoteBuilder_ == null) {
        responseByRemote_ = null;
        onChanged();
      } else {
        responseByRemote_ = null;
        responseByRemoteBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    public generated.Remote.Builder getResponseByRemoteBuilder() {
      
      onChanged();
      return getResponseByRemoteFieldBuilder().getBuilder();
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    public generated.RemoteOrBuilder getResponseByRemoteOrBuilder() {
      if (responseByRemoteBuilder_ != null) {
        return responseByRemoteBuilder_.getMessageOrBuilder();
      } else {
        return responseByRemote_ == null ?
            generated.Remote.getDefaultInstance() : responseByRemote_;
      }
    }
    /**
     * <code>.generated.Remote responseByRemote = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> 
        getResponseByRemoteFieldBuilder() {
      if (responseByRemoteBuilder_ == null) {
        responseByRemoteBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder>(
                getResponseByRemote(),
                getParentForChildren(),
                isClean());
        responseByRemote_ = null;
      }
      return responseByRemoteBuilder_;
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


    // @@protoc_insertion_point(builder_scope:generated.PermissionResponse)
  }

  // @@protoc_insertion_point(class_scope:generated.PermissionResponse)
  private static final generated.PermissionResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.PermissionResponse();
  }

  public static generated.PermissionResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PermissionResponse>
      PARSER = new com.google.protobuf.AbstractParser<PermissionResponse>() {
    @java.lang.Override
    public PermissionResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PermissionResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PermissionResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PermissionResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.PermissionResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

