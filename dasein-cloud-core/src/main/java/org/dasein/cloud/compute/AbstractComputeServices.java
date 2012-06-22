/**
 * Copyright (C) 2009-2012 enStratus Networks Inc.
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.compute;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractComputeServices implements ComputeServices{

    @Override
    public @Nullable  AutoScalingSupport getAutoScalingSupport() {
        return null;
    }

    @Override
    public @Nullable MachineImageSupport getImageSupport() {
        return null;
    }

    @Override
    public @Nullable SnapshotSupport getSnapshotSupport() {
        return null;
    }

    @Override
    public @Nullable VirtualMachineSupport getVirtualMachineSupport() {
        return null;
    }

    @Override
    public @Nullable VolumeSupport getVolumeSupport() {
        return null;
    }

    @Override
    public boolean hasAutoScalingSupport() {
        return (getAutoScalingSupport() != null);
    }

    @Override
    public boolean hasImageSupport() {
        return (getImageSupport() != null);
    }

    @Override
    public boolean hasSnapshotSupport() {
        return (getSnapshotSupport() != null);
    }

    @Override
    public boolean hasVirtualMachineSupport() {
        return (getVirtualMachineSupport() != null);
    }
    
    @Override
    public boolean hasVolumeSupport() {
        return (getVolumeSupport() != null);
    }

}
