// Copyright (c) SharpCrafters s.r.o. See the LICENSE.md file in the root directory of this repository root for details.

using PostSharp.Engineering.BuildTools;
using PostSharp.Engineering.BuildTools.Build.Model;
using PostSharp.Engineering.BuildTools.Build.Solutions;
using PostSharp.Engineering.BuildTools.Dependencies.Definitions;
using MetalamaDependencies = PostSharp.Engineering.BuildTools.Dependencies.Definitions.MetalamaDependencies.V2025_0;

var product = new Product( MetalamaDependencies.MetalamaFrameworkRunTime )
{
    Solutions = [new DotNetSolution( "Metalama.Framework.RunTime.sln" )],
    PublicArtifacts = Pattern.Create( "Metalama.Framework.RunTime.$(PackageVersion).nupkg" ),
    Dependencies = [DevelopmentDependencies.PostSharpEngineering]
};

return new EngineeringApp( product ).Run( args );