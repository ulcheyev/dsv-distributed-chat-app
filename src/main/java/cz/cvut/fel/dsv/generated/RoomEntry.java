// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatService.proto

package generated;

/**
 * Protobuf type {@code generated.RoomEntry}
 */
public final class RoomEntry extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:generated.RoomEntry)
    RoomEntryOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RoomEntry.newBuilder() to construct.
  private RoomEntry(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RoomEntry() {
    roomName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RoomEntry();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RoomEntry(
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
            if (roomOwner_ != null) {
              subBuilder = roomOwner_.toBuilder();
            }
            roomOwner_ = input.readMessage(generated.Remote.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(roomOwner_);
              roomOwner_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            generated.Remote.Builder subBuilder = null;
            if (roomBackup_ != null) {
              subBuilder = roomBackup_.toBuilder();
            }
            roomBackup_ = input.readMessage(generated.Remote.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(roomBackup_);
              roomBackup_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            roomName_ = s;
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
    return generated.ChatService.internal_static_generated_RoomEntry_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.ChatService.internal_static_generated_RoomEntry_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.RoomEntry.class, generated.RoomEntry.Builder.class);
  }

  public static final int ROOMOWNER_FIELD_NUMBER = 1;
  private generated.Remote roomOwner_;
  /**
   * <code>.generated.Remote roomOwner = 1;</code>
   * @return Whether the roomOwner field is set.
   */
  @java.lang.Override
  public boolean hasRoomOwner() {
    return roomOwner_ != null;
  }
  /**
   * <code>.generated.Remote roomOwner = 1;</code>
   * @return The roomOwner.
   */
  @java.lang.Override
  public generated.Remote getRoomOwner() {
    return roomOwner_ == null ? generated.Remote.getDefaultInstance() : roomOwner_;
  }
  /**
   * <code>.generated.Remote roomOwner = 1;</code>
   */
  @java.lang.Override
  public generated.RemoteOrBuilder getRoomOwnerOrBuilder() {
    return getRoomOwner();
  }

  public static final int ROOMBACKUP_FIELD_NUMBER = 2;
  private generated.Remote roomBackup_;
  /**
   * <code>.generated.Remote roomBackup = 2;</code>
   * @return Whether the roomBackup field is set.
   */
  @java.lang.Override
  public boolean hasRoomBackup() {
    return roomBackup_ != null;
  }
  /**
   * <code>.generated.Remote roomBackup = 2;</code>
   * @return The roomBackup.
   */
  @java.lang.Override
  public generated.Remote getRoomBackup() {
    return roomBackup_ == null ? generated.Remote.getDefaultInstance() : roomBackup_;
  }
  /**
   * <code>.generated.Remote roomBackup = 2;</code>
   */
  @java.lang.Override
  public generated.RemoteOrBuilder getRoomBackupOrBuilder() {
    return getRoomBackup();
  }

  public static final int ROOMNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object roomName_;
  /**
   * <code>string roomName = 3;</code>
   * @return The roomName.
   */
  @java.lang.Override
  public java.lang.String getRoomName() {
    java.lang.Object ref = roomName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      roomName_ = s;
      return s;
    }
  }
  /**
   * <code>string roomName = 3;</code>
   * @return The bytes for roomName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getRoomNameBytes() {
    java.lang.Object ref = roomName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      roomName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (roomOwner_ != null) {
      output.writeMessage(1, getRoomOwner());
    }
    if (roomBackup_ != null) {
      output.writeMessage(2, getRoomBackup());
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(roomName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, roomName_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (roomOwner_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRoomOwner());
    }
    if (roomBackup_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getRoomBackup());
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(roomName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, roomName_);
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
    if (!(obj instanceof generated.RoomEntry)) {
      return super.equals(obj);
    }
    generated.RoomEntry other = (generated.RoomEntry) obj;

    if (hasRoomOwner() != other.hasRoomOwner()) return false;
    if (hasRoomOwner()) {
      if (!getRoomOwner()
          .equals(other.getRoomOwner())) return false;
    }
    if (hasRoomBackup() != other.hasRoomBackup()) return false;
    if (hasRoomBackup()) {
      if (!getRoomBackup()
          .equals(other.getRoomBackup())) return false;
    }
    if (!getRoomName()
        .equals(other.getRoomName())) return false;
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
    if (hasRoomOwner()) {
      hash = (37 * hash) + ROOMOWNER_FIELD_NUMBER;
      hash = (53 * hash) + getRoomOwner().hashCode();
    }
    if (hasRoomBackup()) {
      hash = (37 * hash) + ROOMBACKUP_FIELD_NUMBER;
      hash = (53 * hash) + getRoomBackup().hashCode();
    }
    hash = (37 * hash) + ROOMNAME_FIELD_NUMBER;
    hash = (53 * hash) + getRoomName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.RoomEntry parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.RoomEntry parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.RoomEntry parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.RoomEntry parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.RoomEntry parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.RoomEntry parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.RoomEntry parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.RoomEntry parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.RoomEntry parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.RoomEntry parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.RoomEntry parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.RoomEntry parseFrom(
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
  public static Builder newBuilder(generated.RoomEntry prototype) {
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
   * Protobuf type {@code generated.RoomEntry}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:generated.RoomEntry)
      generated.RoomEntryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.ChatService.internal_static_generated_RoomEntry_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.ChatService.internal_static_generated_RoomEntry_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.RoomEntry.class, generated.RoomEntry.Builder.class);
    }

    // Construct using generated.RoomEntry.newBuilder()
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
      if (roomOwnerBuilder_ == null) {
        roomOwner_ = null;
      } else {
        roomOwner_ = null;
        roomOwnerBuilder_ = null;
      }
      if (roomBackupBuilder_ == null) {
        roomBackup_ = null;
      } else {
        roomBackup_ = null;
        roomBackupBuilder_ = null;
      }
      roomName_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.ChatService.internal_static_generated_RoomEntry_descriptor;
    }

    @java.lang.Override
    public generated.RoomEntry getDefaultInstanceForType() {
      return generated.RoomEntry.getDefaultInstance();
    }

    @java.lang.Override
    public generated.RoomEntry build() {
      generated.RoomEntry result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.RoomEntry buildPartial() {
      generated.RoomEntry result = new generated.RoomEntry(this);
      if (roomOwnerBuilder_ == null) {
        result.roomOwner_ = roomOwner_;
      } else {
        result.roomOwner_ = roomOwnerBuilder_.build();
      }
      if (roomBackupBuilder_ == null) {
        result.roomBackup_ = roomBackup_;
      } else {
        result.roomBackup_ = roomBackupBuilder_.build();
      }
      result.roomName_ = roomName_;
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
      if (other instanceof generated.RoomEntry) {
        return mergeFrom((generated.RoomEntry)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.RoomEntry other) {
      if (other == generated.RoomEntry.getDefaultInstance()) return this;
      if (other.hasRoomOwner()) {
        mergeRoomOwner(other.getRoomOwner());
      }
      if (other.hasRoomBackup()) {
        mergeRoomBackup(other.getRoomBackup());
      }
      if (!other.getRoomName().isEmpty()) {
        roomName_ = other.roomName_;
        onChanged();
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
      generated.RoomEntry parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.RoomEntry) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private generated.Remote roomOwner_;
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> roomOwnerBuilder_;
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     * @return Whether the roomOwner field is set.
     */
    public boolean hasRoomOwner() {
      return roomOwnerBuilder_ != null || roomOwner_ != null;
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     * @return The roomOwner.
     */
    public generated.Remote getRoomOwner() {
      if (roomOwnerBuilder_ == null) {
        return roomOwner_ == null ? generated.Remote.getDefaultInstance() : roomOwner_;
      } else {
        return roomOwnerBuilder_.getMessage();
      }
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    public Builder setRoomOwner(generated.Remote value) {
      if (roomOwnerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        roomOwner_ = value;
        onChanged();
      } else {
        roomOwnerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    public Builder setRoomOwner(
        generated.Remote.Builder builderForValue) {
      if (roomOwnerBuilder_ == null) {
        roomOwner_ = builderForValue.build();
        onChanged();
      } else {
        roomOwnerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    public Builder mergeRoomOwner(generated.Remote value) {
      if (roomOwnerBuilder_ == null) {
        if (roomOwner_ != null) {
          roomOwner_ =
            generated.Remote.newBuilder(roomOwner_).mergeFrom(value).buildPartial();
        } else {
          roomOwner_ = value;
        }
        onChanged();
      } else {
        roomOwnerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    public Builder clearRoomOwner() {
      if (roomOwnerBuilder_ == null) {
        roomOwner_ = null;
        onChanged();
      } else {
        roomOwner_ = null;
        roomOwnerBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    public generated.Remote.Builder getRoomOwnerBuilder() {
      
      onChanged();
      return getRoomOwnerFieldBuilder().getBuilder();
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    public generated.RemoteOrBuilder getRoomOwnerOrBuilder() {
      if (roomOwnerBuilder_ != null) {
        return roomOwnerBuilder_.getMessageOrBuilder();
      } else {
        return roomOwner_ == null ?
            generated.Remote.getDefaultInstance() : roomOwner_;
      }
    }
    /**
     * <code>.generated.Remote roomOwner = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> 
        getRoomOwnerFieldBuilder() {
      if (roomOwnerBuilder_ == null) {
        roomOwnerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder>(
                getRoomOwner(),
                getParentForChildren(),
                isClean());
        roomOwner_ = null;
      }
      return roomOwnerBuilder_;
    }

    private generated.Remote roomBackup_;
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> roomBackupBuilder_;
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     * @return Whether the roomBackup field is set.
     */
    public boolean hasRoomBackup() {
      return roomBackupBuilder_ != null || roomBackup_ != null;
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     * @return The roomBackup.
     */
    public generated.Remote getRoomBackup() {
      if (roomBackupBuilder_ == null) {
        return roomBackup_ == null ? generated.Remote.getDefaultInstance() : roomBackup_;
      } else {
        return roomBackupBuilder_.getMessage();
      }
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    public Builder setRoomBackup(generated.Remote value) {
      if (roomBackupBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        roomBackup_ = value;
        onChanged();
      } else {
        roomBackupBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    public Builder setRoomBackup(
        generated.Remote.Builder builderForValue) {
      if (roomBackupBuilder_ == null) {
        roomBackup_ = builderForValue.build();
        onChanged();
      } else {
        roomBackupBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    public Builder mergeRoomBackup(generated.Remote value) {
      if (roomBackupBuilder_ == null) {
        if (roomBackup_ != null) {
          roomBackup_ =
            generated.Remote.newBuilder(roomBackup_).mergeFrom(value).buildPartial();
        } else {
          roomBackup_ = value;
        }
        onChanged();
      } else {
        roomBackupBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    public Builder clearRoomBackup() {
      if (roomBackupBuilder_ == null) {
        roomBackup_ = null;
        onChanged();
      } else {
        roomBackup_ = null;
        roomBackupBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    public generated.Remote.Builder getRoomBackupBuilder() {
      
      onChanged();
      return getRoomBackupFieldBuilder().getBuilder();
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    public generated.RemoteOrBuilder getRoomBackupOrBuilder() {
      if (roomBackupBuilder_ != null) {
        return roomBackupBuilder_.getMessageOrBuilder();
      } else {
        return roomBackup_ == null ?
            generated.Remote.getDefaultInstance() : roomBackup_;
      }
    }
    /**
     * <code>.generated.Remote roomBackup = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder> 
        getRoomBackupFieldBuilder() {
      if (roomBackupBuilder_ == null) {
        roomBackupBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            generated.Remote, generated.Remote.Builder, generated.RemoteOrBuilder>(
                getRoomBackup(),
                getParentForChildren(),
                isClean());
        roomBackup_ = null;
      }
      return roomBackupBuilder_;
    }

    private java.lang.Object roomName_ = "";
    /**
     * <code>string roomName = 3;</code>
     * @return The roomName.
     */
    public java.lang.String getRoomName() {
      java.lang.Object ref = roomName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        roomName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string roomName = 3;</code>
     * @return The bytes for roomName.
     */
    public com.google.protobuf.ByteString
        getRoomNameBytes() {
      java.lang.Object ref = roomName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        roomName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string roomName = 3;</code>
     * @param value The roomName to set.
     * @return This builder for chaining.
     */
    public Builder setRoomName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      roomName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string roomName = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearRoomName() {
      
      roomName_ = getDefaultInstance().getRoomName();
      onChanged();
      return this;
    }
    /**
     * <code>string roomName = 3;</code>
     * @param value The bytes for roomName to set.
     * @return This builder for chaining.
     */
    public Builder setRoomNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      roomName_ = value;
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


    // @@protoc_insertion_point(builder_scope:generated.RoomEntry)
  }

  // @@protoc_insertion_point(class_scope:generated.RoomEntry)
  private static final generated.RoomEntry DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.RoomEntry();
  }

  public static generated.RoomEntry getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RoomEntry>
      PARSER = new com.google.protobuf.AbstractParser<RoomEntry>() {
    @java.lang.Override
    public RoomEntry parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RoomEntry(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RoomEntry> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RoomEntry> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.RoomEntry getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

