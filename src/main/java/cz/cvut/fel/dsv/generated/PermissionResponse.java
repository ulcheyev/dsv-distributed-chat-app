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
    deferredRequests_ = java.util.Collections.emptyList();
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
    int mutable_bitField0_ = 0;
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
          case 8: {

            granted_ = input.readBool();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              deferredRequests_ = new java.util.ArrayList<generated.PermissionRequest>();
              mutable_bitField0_ |= 0x00000001;
            }
            deferredRequests_.add(
                input.readMessage(generated.PermissionRequest.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        deferredRequests_ = java.util.Collections.unmodifiableList(deferredRequests_);
      }
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

  public static final int GRANTED_FIELD_NUMBER = 1;
  private boolean granted_;
  /**
   * <code>bool granted = 1;</code>
   * @return The granted.
   */
  @java.lang.Override
  public boolean getGranted() {
    return granted_;
  }

  public static final int DEFERREDREQUESTS_FIELD_NUMBER = 2;
  private java.util.List<generated.PermissionRequest> deferredRequests_;
  /**
   * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
   */
  @java.lang.Override
  public java.util.List<generated.PermissionRequest> getDeferredRequestsList() {
    return deferredRequests_;
  }
  /**
   * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends generated.PermissionRequestOrBuilder> 
      getDeferredRequestsOrBuilderList() {
    return deferredRequests_;
  }
  /**
   * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
   */
  @java.lang.Override
  public int getDeferredRequestsCount() {
    return deferredRequests_.size();
  }
  /**
   * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
   */
  @java.lang.Override
  public generated.PermissionRequest getDeferredRequests(int index) {
    return deferredRequests_.get(index);
  }
  /**
   * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
   */
  @java.lang.Override
  public generated.PermissionRequestOrBuilder getDeferredRequestsOrBuilder(
      int index) {
    return deferredRequests_.get(index);
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
    if (granted_ != false) {
      output.writeBool(1, granted_);
    }
    for (int i = 0; i < deferredRequests_.size(); i++) {
      output.writeMessage(2, deferredRequests_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (granted_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, granted_);
    }
    for (int i = 0; i < deferredRequests_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, deferredRequests_.get(i));
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

    if (getGranted()
        != other.getGranted()) return false;
    if (!getDeferredRequestsList()
        .equals(other.getDeferredRequestsList())) return false;
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
    hash = (37 * hash) + GRANTED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getGranted());
    if (getDeferredRequestsCount() > 0) {
      hash = (37 * hash) + DEFERREDREQUESTS_FIELD_NUMBER;
      hash = (53 * hash) + getDeferredRequestsList().hashCode();
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
        getDeferredRequestsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      granted_ = false;

      if (deferredRequestsBuilder_ == null) {
        deferredRequests_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        deferredRequestsBuilder_.clear();
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
      int from_bitField0_ = bitField0_;
      result.granted_ = granted_;
      if (deferredRequestsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          deferredRequests_ = java.util.Collections.unmodifiableList(deferredRequests_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.deferredRequests_ = deferredRequests_;
      } else {
        result.deferredRequests_ = deferredRequestsBuilder_.build();
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
      if (other.getGranted() != false) {
        setGranted(other.getGranted());
      }
      if (deferredRequestsBuilder_ == null) {
        if (!other.deferredRequests_.isEmpty()) {
          if (deferredRequests_.isEmpty()) {
            deferredRequests_ = other.deferredRequests_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureDeferredRequestsIsMutable();
            deferredRequests_.addAll(other.deferredRequests_);
          }
          onChanged();
        }
      } else {
        if (!other.deferredRequests_.isEmpty()) {
          if (deferredRequestsBuilder_.isEmpty()) {
            deferredRequestsBuilder_.dispose();
            deferredRequestsBuilder_ = null;
            deferredRequests_ = other.deferredRequests_;
            bitField0_ = (bitField0_ & ~0x00000001);
            deferredRequestsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getDeferredRequestsFieldBuilder() : null;
          } else {
            deferredRequestsBuilder_.addAllMessages(other.deferredRequests_);
          }
        }
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
    private int bitField0_;

    private boolean granted_ ;
    /**
     * <code>bool granted = 1;</code>
     * @return The granted.
     */
    @java.lang.Override
    public boolean getGranted() {
      return granted_;
    }
    /**
     * <code>bool granted = 1;</code>
     * @param value The granted to set.
     * @return This builder for chaining.
     */
    public Builder setGranted(boolean value) {
      
      granted_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool granted = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearGranted() {
      
      granted_ = false;
      onChanged();
      return this;
    }

    private java.util.List<generated.PermissionRequest> deferredRequests_ =
      java.util.Collections.emptyList();
    private void ensureDeferredRequestsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        deferredRequests_ = new java.util.ArrayList<generated.PermissionRequest>(deferredRequests_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        generated.PermissionRequest, generated.PermissionRequest.Builder, generated.PermissionRequestOrBuilder> deferredRequestsBuilder_;

    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public java.util.List<generated.PermissionRequest> getDeferredRequestsList() {
      if (deferredRequestsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(deferredRequests_);
      } else {
        return deferredRequestsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public int getDeferredRequestsCount() {
      if (deferredRequestsBuilder_ == null) {
        return deferredRequests_.size();
      } else {
        return deferredRequestsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public generated.PermissionRequest getDeferredRequests(int index) {
      if (deferredRequestsBuilder_ == null) {
        return deferredRequests_.get(index);
      } else {
        return deferredRequestsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder setDeferredRequests(
        int index, generated.PermissionRequest value) {
      if (deferredRequestsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeferredRequestsIsMutable();
        deferredRequests_.set(index, value);
        onChanged();
      } else {
        deferredRequestsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder setDeferredRequests(
        int index, generated.PermissionRequest.Builder builderForValue) {
      if (deferredRequestsBuilder_ == null) {
        ensureDeferredRequestsIsMutable();
        deferredRequests_.set(index, builderForValue.build());
        onChanged();
      } else {
        deferredRequestsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder addDeferredRequests(generated.PermissionRequest value) {
      if (deferredRequestsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeferredRequestsIsMutable();
        deferredRequests_.add(value);
        onChanged();
      } else {
        deferredRequestsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder addDeferredRequests(
        int index, generated.PermissionRequest value) {
      if (deferredRequestsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeferredRequestsIsMutable();
        deferredRequests_.add(index, value);
        onChanged();
      } else {
        deferredRequestsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder addDeferredRequests(
        generated.PermissionRequest.Builder builderForValue) {
      if (deferredRequestsBuilder_ == null) {
        ensureDeferredRequestsIsMutable();
        deferredRequests_.add(builderForValue.build());
        onChanged();
      } else {
        deferredRequestsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder addDeferredRequests(
        int index, generated.PermissionRequest.Builder builderForValue) {
      if (deferredRequestsBuilder_ == null) {
        ensureDeferredRequestsIsMutable();
        deferredRequests_.add(index, builderForValue.build());
        onChanged();
      } else {
        deferredRequestsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder addAllDeferredRequests(
        java.lang.Iterable<? extends generated.PermissionRequest> values) {
      if (deferredRequestsBuilder_ == null) {
        ensureDeferredRequestsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, deferredRequests_);
        onChanged();
      } else {
        deferredRequestsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder clearDeferredRequests() {
      if (deferredRequestsBuilder_ == null) {
        deferredRequests_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        deferredRequestsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public Builder removeDeferredRequests(int index) {
      if (deferredRequestsBuilder_ == null) {
        ensureDeferredRequestsIsMutable();
        deferredRequests_.remove(index);
        onChanged();
      } else {
        deferredRequestsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public generated.PermissionRequest.Builder getDeferredRequestsBuilder(
        int index) {
      return getDeferredRequestsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public generated.PermissionRequestOrBuilder getDeferredRequestsOrBuilder(
        int index) {
      if (deferredRequestsBuilder_ == null) {
        return deferredRequests_.get(index);  } else {
        return deferredRequestsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public java.util.List<? extends generated.PermissionRequestOrBuilder> 
         getDeferredRequestsOrBuilderList() {
      if (deferredRequestsBuilder_ != null) {
        return deferredRequestsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(deferredRequests_);
      }
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public generated.PermissionRequest.Builder addDeferredRequestsBuilder() {
      return getDeferredRequestsFieldBuilder().addBuilder(
          generated.PermissionRequest.getDefaultInstance());
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public generated.PermissionRequest.Builder addDeferredRequestsBuilder(
        int index) {
      return getDeferredRequestsFieldBuilder().addBuilder(
          index, generated.PermissionRequest.getDefaultInstance());
    }
    /**
     * <code>repeated .generated.PermissionRequest deferredRequests = 2;</code>
     */
    public java.util.List<generated.PermissionRequest.Builder> 
         getDeferredRequestsBuilderList() {
      return getDeferredRequestsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        generated.PermissionRequest, generated.PermissionRequest.Builder, generated.PermissionRequestOrBuilder> 
        getDeferredRequestsFieldBuilder() {
      if (deferredRequestsBuilder_ == null) {
        deferredRequestsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            generated.PermissionRequest, generated.PermissionRequest.Builder, generated.PermissionRequestOrBuilder>(
                deferredRequests_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        deferredRequests_ = null;
      }
      return deferredRequestsBuilder_;
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

